package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.mapper.toUserEntity
import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.user.entity.UserEntity
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvi.sharednotes.storage.db.LocalUserDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataStore: RemoteUserDataStore,
    private val localDataStore: LocalUserDataStore
) : Repository {

    override suspend fun get(credentials: UserCredentials): Flow<UserEntity> =
        remoteDataStore.read(
            credentials.toUserEntity()
        ).onEach {
            // todo: replace with update
            localDataStore.create(it.toUserEntity())
        }

    override suspend fun create(credentials: UserCredentials): Flow<UserEntity> =
        remoteDataStore.create(
            credentials.toUserEntity()
        ).onEach {
            localDataStore.create(it.toUserEntity())
        }
}