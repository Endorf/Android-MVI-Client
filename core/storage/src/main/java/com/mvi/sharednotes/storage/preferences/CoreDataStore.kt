package com.mvi.sharednotes.storage.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

abstract class CoreDataStore<T>(
    private val context: Context,
    preferencesDataStoreName: String
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = preferencesDataStoreName)

    protected suspend fun put(storage: (preferences: MutablePreferences) -> Unit) {
        context.dataStore.edit { preferences ->
            storage(preferences)
        }
    }

    protected suspend fun get(key: Preferences.Key<*>): Any {
        return context.dataStore.data
            .map { preferences ->
                preferences[key] ?: ""
            }.first()
    }

    protected suspend fun get(storage: (preferences: Preferences) -> T) =
        context.dataStore.data.map { preferences ->
            storage(preferences)
        }.first()
}