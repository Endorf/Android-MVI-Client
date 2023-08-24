package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.data.entity.mapper.toLocalEntity
import com.mvi.sharednotes.login.data.entity.mapper.toUser
import com.mvi.sharednotes.login.exception.AuthorizationException
import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationRequest
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.network.data.storage.RemoteAuthDataStore
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvi.sharednotes.storage.AuthDataStore
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.di.qualifier.Local
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteAuthDataStore: RemoteAuthDataStore,
    private val remoteUserDataStore: RemoteUserDataStore,
    @Local private val localDataStore: UserDataStore,
    @Shared private val sharedUserDataStore: UserDataStore,
    @Shared private val sharedAuthDataStore: AuthDataStore
) : Repository {

    override suspend fun signIn(credentials: UserCredentials): Flow<User> = flow {
        // TODO: move to interceptor
        val tokenData = provideAuthData(credentials.email, credentials.password)
        val user = provideUser(tokenData)

        emit(user)
    }

    private suspend fun provideAuthData(email: String, password: String): AuthenticationResponse {
        val accessTokenResponse: AuthenticationResponse? = remoteAuthDataStore.signIn(
            AuthenticationRequest(email, password)
        ).getOrNull()

        accessTokenResponse?.toLocalEntity()?.let {
            sharedAuthDataStore.put(it)
        } ?: throw AuthorizationException()

        return accessTokenResponse
    }

    private suspend fun provideUser(tokenData: AuthenticationResponse): User {
        return remoteUserDataStore.getCurrentUser(tokenData.access_token).toUser()
    }
}