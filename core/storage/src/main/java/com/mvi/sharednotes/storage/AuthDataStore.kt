package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.entities.AuthenticationEntity

interface AuthDataStore {

    suspend fun put(data: AuthenticationEntity)

    suspend fun clear()

    suspend fun getAccessToken(): String?

    suspend fun hasAccessTokenExpired(): Boolean

    suspend fun getRefreshToken(): String?

    suspend fun hasRefreshTokenExpired(): Boolean
}