package com.mvi.sharednotes.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.data.entity.mapper.toLocalUserEntity
import com.mvi.sharednotes.login.data.entity.mapper.toUser
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InitializationRepository @Inject constructor(
    private val remoteUserDataStore: RemoteUserDataStore,
    @Shared private val sharedUserDataStore: UserDataStore
) : Repository {

    override fun get(): Flow<User?> = flow {
        remoteUserDataStore.getCurrentUser()
            .also { sharedUserDataStore.put(it.toLocalUserEntity()) }
            .also { emit(it.toUser()) }
    }.catch {
        emit(User())
    }
}