package com.mvi.sharednotes.storage.preferences

import android.content.Context
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mvi.sharednotes.storage.AuthDataStore
import com.mvi.sharednotes.storage.BuildConfig
import com.mvi.sharednotes.storage.entities.AuthenticationEntity
import javax.inject.Inject

class SharedAuthDataStoreImpl @Inject constructor(
    context: Context
) : CoreDataStore<AuthenticationEntity>(context, STORAGE_NAME), AuthDataStore {

    override suspend fun put(data: AuthenticationEntity) {
        val currentTime = System.currentTimeMillis()

        put { preferences ->
            preferences[ACCESS_TOKEN_KEY] = data.access_token
            preferences[ACCESS_TOKEN_EXP_KEY] = currentTime + data.expiresInMillis
            preferences[REFRESH_TOKEN_KEY] = data.refresh_token
            preferences[REFRESH_TOKEN_EXP_KEY] = currentTime + data.refreshExpiresInInMillis
        }
    }

    override suspend fun clear() {
        clearStorage()
    }

    override suspend fun getAccessToken(): String? = get(ACCESS_TOKEN_KEY) as? String

    override suspend fun hasAccessTokenExpired(): Boolean {
        val expTime = get(ACCESS_TOKEN_EXP_KEY) as? Long ?: 0
        val currentTime = System.currentTimeMillis()
        return expTime < currentTime
    }

    override suspend fun getRefreshToken(): String? = get(REFRESH_TOKEN_KEY) as? String

    override suspend fun hasRefreshTokenExpired(): Boolean {
        val expTime = get(REFRESH_TOKEN_EXP_KEY) as? Long ?: 0
        val currentTime = System.currentTimeMillis()
        return expTime < currentTime
    }

    private companion object {

        private const val STORAGE_NAME = BuildConfig.DS_NAME + "_auth"

        private const val ACCESS_TOKEN = "u_access_token"
        private val ACCESS_TOKEN_KEY = stringPreferencesKey(ACCESS_TOKEN)

        private const val ACCESS_TOKEN_EXP = "access_exp_time"
        private val ACCESS_TOKEN_EXP_KEY = longPreferencesKey(ACCESS_TOKEN_EXP)

        private const val REFRESH_TOKEN = "u_refresh_token"
        private val REFRESH_TOKEN_KEY = stringPreferencesKey(REFRESH_TOKEN)

        private const val REFRESH_TOKEN_EXP = "refresh_exp_time"
        private val REFRESH_TOKEN_EXP_KEY = longPreferencesKey(REFRESH_TOKEN_EXP)
    }
}