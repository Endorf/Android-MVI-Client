package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.view.entity.UserCredentials
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun get(credentials: UserCredentials): Flow<User>

    suspend fun create(credentials: UserCredentials): Flow<User>
}