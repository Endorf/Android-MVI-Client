package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDataStore {

    fun create(user: UserEntity)

    fun get(user: UserEntity): Flow<UserEntity>

    fun get(): Flow<UserEntity>

    fun update(user: UserEntity)
}