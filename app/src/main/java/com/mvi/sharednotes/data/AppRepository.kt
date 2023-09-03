package com.mvi.sharednotes.data

import com.mvi.sharednotes.network.data.storage.RemoteAuthDataStore
import com.mvi.sharednotes.storage.AuthDataStore
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Suppress("UnusedPrivateMember")
class AppRepository @Inject constructor(
    private val remoteAuthDataStore: RemoteAuthDataStore,
    @Shared private val sharedUserDataStore: UserDataStore,
    @Shared private val sharedAuthDataStore: AuthDataStore
) {

    suspend fun logout(): Flow<Boolean> = flow {
        sharedAuthDataStore.getRefreshToken()
            ?.let { token ->
                val result = remoteAuthDataStore.logout(token)
                emit(result.isSuccess)
            }
            ?: emit(true)
    }

    suspend fun clearUserSpace() {
//        sharedUserDataStore.clean() TODO: clean user data
        sharedAuthDataStore.clear()
    }
}