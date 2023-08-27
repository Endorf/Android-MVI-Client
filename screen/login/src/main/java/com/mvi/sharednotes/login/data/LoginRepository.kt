package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.network.data.api.auth.entity.mapper.toLocalEntity
import com.mvi.sharednotes.login.data.entity.mapper.toLocalUserEntity
import com.mvi.sharednotes.login.data.entity.mapper.toUser
import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationRequest
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.network.data.storage.RemoteAuthDataStore
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvi.sharednotes.storage.AuthDataStore
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteAuthDataStore: RemoteAuthDataStore,
    private val remoteUserDataStore: RemoteUserDataStore,
    @Shared private val sharedUserDataStore: UserDataStore,
    @Shared private val sharedAuthDataStore: AuthDataStore
) : Repository {

    override suspend fun signIn(credentials: UserCredentials): Flow<User> = flow {
        provideAuthData(credentials.email, credentials.password)

        remoteUserDataStore.getCurrentUser()
            .also { sharedUserDataStore.put(it.toLocalUserEntity()) }
            .also { emit(it.toUser()) }
    }

    private suspend fun provideAuthData(email: String, password: String) {
        val accessTokenResponse: AuthenticationResponse? = remoteAuthDataStore.signIn(
            AuthenticationRequest(email, password)
        ).getOrNull()

        accessTokenResponse?.toLocalEntity()?.let {
            sharedAuthDataStore.put(it)
        } ?: sharedAuthDataStore.clear()
    }
}