package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.mapper.toUserEntity
import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.user.entity.UserEntity
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataStore: RemoteUserDataStore
) : Repository {

    override suspend fun get(credentials: UserCredentials): Flow<UserEntity> =
        remoteDataStore.read(
            credentials.toUserEntity()
        )

    override suspend fun create(credentials: UserCredentials): Flow<UserEntity> =
        remoteDataStore.create(
            credentials.toUserEntity()
        )
}