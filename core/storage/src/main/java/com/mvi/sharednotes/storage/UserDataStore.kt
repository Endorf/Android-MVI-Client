package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.entities.LocalUserEntity

interface UserDataStore {

    suspend fun put(user: LocalUserEntity)

    suspend fun get(user: LocalUserEntity? = null): LocalUserEntity?
}