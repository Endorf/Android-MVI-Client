package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.user.UserApi
import com.mvi.sharednotes.network.data.api.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteUserDataStoreImpl @Inject constructor(
    private val api: UserApi
) : RemoteUserDataStore {

    override suspend fun create(user: UserEntity): Flow<UserEntity> = flow {
        val newUser = api.create(user)
        emit(newUser)
    }

    override suspend fun read(user: UserEntity): Flow<UserEntity> = flow {
        val remoteUser = api.get(user.id)
        emit(remoteUser)
    }

}