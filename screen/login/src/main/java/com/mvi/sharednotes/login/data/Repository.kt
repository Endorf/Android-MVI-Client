package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.view.entity.UserCredentials
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun signIn(credentials: UserCredentials): Flow<User>
}