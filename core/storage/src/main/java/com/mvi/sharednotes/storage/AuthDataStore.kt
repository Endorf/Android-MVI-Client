package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.entities.AuthenticationEntity

interface AuthDataStore {

    suspend fun put(data: AuthenticationEntity)

    suspend fun getAccessToken(): String

    suspend fun getRefreshToken(): String
}