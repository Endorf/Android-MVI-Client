package com.mvi.sharednotes.storage.preferences

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mvi.sharednotes.storage.BuildConfig
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.entities.LocalUserEntity
import javax.inject.Inject

class SharedUserDataStoreImpl @Inject constructor(
    context: Context
) : CoreDataStore<LocalUserEntity>(context, STORAGE_NAME), UserDataStore {

    override suspend fun put(user: LocalUserEntity) {
        put { preferences ->
            preferences[ID_KEY] = user.userId
            preferences[EMAIL_KEY] = user.email
            user.userName?.let { preferences[USERNAME_KEY] = user.userName }
        }
    }

    override suspend fun get(user: LocalUserEntity?): LocalUserEntity {
        return get { preferences ->
            LocalUserEntity(
                userId = preferences[ID_KEY] ?: NONE_USER_ID,
                email = preferences[EMAIL_KEY] ?: "",
                userName = preferences[USERNAME_KEY]
            )
        }
    }

    companion object {
        private const val STORAGE_NAME = BuildConfig.DS_NAME + "_user"

        private const val USER_ID = "current_user_id"
        private val ID_KEY = intPreferencesKey(USER_ID)

        private const val EMAIL = "current_user_email"
        private val EMAIL_KEY = stringPreferencesKey(EMAIL)

        private const val USERNAME = "current_user_nickname"
        private val USERNAME_KEY = stringPreferencesKey(USERNAME)

        private const val NONE_USER_ID = -1
    }
}