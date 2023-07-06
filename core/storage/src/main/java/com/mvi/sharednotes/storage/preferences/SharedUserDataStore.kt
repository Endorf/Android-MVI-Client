package com.mvi.sharednotes.storage.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mvi.sharednotes.storage.BuildConfig
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.entities.LocalUserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SharedUserDataStore @Inject constructor(
    private val context: Context
) : UserDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORAGE_NAME)

    override suspend fun create(user: LocalUserEntity) {
        Log.e("test", "create: $user")
        context.dataStore.edit { preferences ->
            preferences[ID_KEY] = user.userId
            preferences[EMAIL_KEY] = user.email
            user.userName?.let { preferences[USERNAME_KEY] = user.userName }
        }
    }

    override suspend fun get(user: LocalUserEntity?): LocalUserEntity {
        return context.dataStore.data
            .map { preferences ->
                LocalUserEntity(
                    userId = preferences[ID_KEY] ?: NONE_USER_ID,
                    email = preferences[EMAIL_KEY] ?: "",
                    userName = preferences[USERNAME_KEY]
                )
            }.first()
    }

    override suspend fun update(user: LocalUserEntity) = TODO("Not yet implemented")

    private companion object {

        private const val STORAGE_NAME = BuildConfig.DS_NAME

        private const val EMAIL = "current_user_email"
        private val EMAIL_KEY = stringPreferencesKey(EMAIL)

        private const val USERNAME = "current_user_nickname"
        private val USERNAME_KEY = stringPreferencesKey(USERNAME)

        private const val USER_ID = "current_user_id"
        private val ID_KEY = intPreferencesKey(USER_ID)

        private const val NONE_USER_ID = -1
    }
}