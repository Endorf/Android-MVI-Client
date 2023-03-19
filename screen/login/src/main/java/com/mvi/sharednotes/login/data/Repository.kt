package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun get(credentials: UserCredentials): Flow<UserEntity>

    suspend fun create(credentials: UserCredentials): Flow<UserEntity>
}