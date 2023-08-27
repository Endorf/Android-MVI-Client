package com.mvi.sharednotes.network.data.utils.network

import com.google.gson.Gson
import com.mvi.sharednotes.network.BuildConfig
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.network.data.api.auth.entity.mapper.toLocalEntity
import com.mvi.sharednotes.network.data.utils.network.HttpBodyFormat.Companion.toMediaType
import com.mvi.sharednotes.storage.AuthDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.internal.closeQuietly
import org.json.JSONObject
import java.net.HttpURLConnection
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthInterceptor @Inject constructor(
    @Shared private val sharedAuthDataStore: AuthDataStore
) : Interceptor, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        buildRequest(chain)
    )

    private fun buildRequest(chain: Interceptor.Chain): Request = runBlocking {
        when {
            sharedAuthDataStore.isAuthorized().not() -> chain.request()
            sharedAuthDataStore.hasRefreshTokenExpired() -> {
                sharedAuthDataStore.clear()
                chain.request()
            }

            sharedAuthDataStore.hasAccessTokenExpired() -> {
                refreshAccessToken(chain)
                buildRequest(chain)
            }

            else -> chain.request().toAuthRequest(sharedAuthDataStore.getAccessToken())
        }
    }

    private suspend fun refreshAccessToken(chain: Interceptor.Chain) {
        val response = chain.proceedAuthorized(
            sharedAuthDataStore.getRefreshToken()
        ) { token ->
            val type = HttpBodyFormat.JSON.toMediaType()
            with(JSONObject()) {
                put(TOKEN_KEY, token)
                toString().toRequestBody(type)
            }
        }

        if (response.code == HttpURLConnection.HTTP_OK) {
            sharedAuthDataStore.put(response.parseAuthEntity())
        } else {
            sharedAuthDataStore.clear()
        }
        response.body?.closeQuietly()
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER = "Bearer"

        private const val AUTH_URL = "${BuildConfig.BASE_URL}/auth/refresh"
        private const val TOKEN_KEY = "token"

        private fun Interceptor.Chain.proceedAuthorized(
            token: String?,
            authorizedBody: (String) -> RequestBody
        ): Response {
            val request = request().newBuilder()
                .post(authorizedBody(token ?: ""))
                .url(AUTH_URL)
                .build()
            return proceed(request)
        }

        private fun Request.toAuthRequest(token: String?) = takeIf { !token.isNullOrBlank() }
            ?.run {
                newBuilder()
                    .header(AUTHORIZATION_HEADER, "$BEARER $token")
                    .build()
            } ?: this

        private fun Response.parseAuthEntity() = Gson().fromJson(
            body?.string(),
            AuthenticationResponse::class.java
        ).toLocalEntity()
    }
}
