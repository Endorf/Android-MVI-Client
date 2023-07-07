package com.mvi.sharednotes.creation.data

import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.db.LocalNoteDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import com.mvi.sharednotes.storage.entities.LocalPostEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val localNoteDataStore: LocalNoteDataStore,
    @Shared private val sharedUserDataStore: UserDataStore
) : Repository {

    @Suppress("MagicNumber")
    override suspend fun create(title: String, description: String, tag: String) {
        sharedUserDataStore.get()?.let {
            LocalPostEntity(
                it.userId,
                it.userName ?: LocalPostEntity.UNKNOWN,
                tag,
                title,
                description
            ).let {
                localNoteDataStore.create(it)
            }
        }
    }
}