package com.mvi.sharednotes.data

import com.mvi.sharednotes.login.data.entity.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun get(): Flow<User?>
}