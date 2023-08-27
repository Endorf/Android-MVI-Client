package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.auth.AuthApi
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationRequest
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
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
                NullPointerException("some network error")
            )

            HttpURLConnection.HTTP_OK -> Result.success(response.body()!!)
            else -> Result.failure(NullPointerException("something went wrong"))
        }
    }
}