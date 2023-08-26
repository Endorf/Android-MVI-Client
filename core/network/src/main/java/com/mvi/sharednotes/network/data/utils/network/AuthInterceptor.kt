package com.mvi.sharednotes.network.data.utils.network

import com.mvi.sharednotes.storage.AuthDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthInterceptor @Inject constructor(
    @Shared private val sharedAuthDataStore: AuthDataStore
) : Interceptor, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = buildRequest(chain)

        return chain.proceed(request)
    }

    private fun buildRequest(chain: Interceptor.Chain): Request = runBlocking {
        when {
            sharedAuthDataStore.getAccessToken().isNullOrBlank() -> chain.request()
            sharedAuthDataStore.hasAccessTokenExpired() -> TODO("refresh accessToken doIT()")
            sharedAuthDataStore.hasRefreshTokenExpired() -> TODO("force logout")
            else -> chain.request(sharedAuthDataStore.getAccessToken())
        }
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER = "Bearer"

        private fun Interceptor.Chain.request(token: String?) = takeIf { !token.isNullOrBlank() }
            ?.run {
                request().newBuilder()
                    .header(AUTHORIZATION_HEADER, "$BEARER $token")
                    .build()
            } ?: request()
    }
}