package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.entities.LocalUserEntity

interface UserDataStore {

    suspend fun create(user: LocalUserEntity)

    suspend fun get(user: LocalUserEntity? = null): LocalUserEntity?

    suspend fun update(user: LocalUserEntity)
}