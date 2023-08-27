package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationRequest
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse

interface RemoteAuthDataStore {

    suspend fun signIn(request: AuthenticationRequest): Result<AuthenticationResponse>
}