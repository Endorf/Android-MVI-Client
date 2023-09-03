package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.auth.AuthApi
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationRequest
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.network.data.api.auth.entity.LogoutRequest
import java.net.HttpURLConnection
import javax.inject.Inject

class RemoteAuthDataStoreImpl @Inject constructor(
    private val api: AuthApi
) : RemoteAuthDataStore {

    override suspend fun signIn(request: AuthenticationRequest): Result<AuthenticationResponse> {
        val response = api.signIn(request)

        // TODO: update error handling
        return when (response.code()) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> Result.failure(
                NullPointerException("HTTP_UNAUTHORIZED signIn")
            )

            HttpURLConnection.HTTP_OK -> Result.success(response.body()!!)
            else -> Result.failure(NullPointerException("something went wrong"))
        }
    }

    override suspend fun logout(token: String): Result<Unit> {
        val response = api.logout(LogoutRequest(token))

        return when (response.code()) {
            HttpURLConnection.HTTP_UNAUTHORIZED -> Result.failure(
                NullPointerException("HTTP_UNAUTHORIZED logout")
            )

            HttpURLConnection.HTTP_NO_CONTENT -> {
                Result.success(Unit)
            }

            else -> Result.failure(NullPointerException("something went wrong"))
        }
    }
}