package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface RemoteUserDataStore {

    suspend fun create(user: UserEntity): Flow<UserEntity>

    suspend fun read(user: UserEntity): Flow<UserEntity>
}