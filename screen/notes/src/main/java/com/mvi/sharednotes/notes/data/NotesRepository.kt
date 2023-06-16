package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.network.data.storage.RemotePostDataStore
import com.mvi.sharednotes.notes.data.mappers.toNote
import com.mvi.sharednotes.notes.data.mappers.toNoteEntities
import com.mvi.sharednotes.notes.data.mappers.toNoteEntity
import com.mvi.sharednotes.notes.data.mappers.toNotes
import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.storage.db.LocalNoteDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val remoteDataStore: RemotePostDataStore,
    private val localDataStore: LocalNoteDataStore
) : Repository {

    override suspend fun get(): Flow<List<Note>> {
        val local = localDataStore.get().map { it.toNote() }
        return remoteDataStore.read()
            .map { it.toNotes() }
            .onStart { emit(local) }
            .onEach { remoteNotes ->
                remoteNotes.filter { it !in local }
                    .toNoteEntities()
                    .let { localDataStore.create(it) }
            }
    }

    override suspend fun get(id: Long): Flow<Note> {
        val local = localDataStore.get().map { it.toNote() }

        return remoteDataStore.read(id)
            .map { it.toNote() }
            .onEach { remoteNotes ->
                if (remoteNotes !in local) {
                    remoteNotes.toNoteEntity().let { localDataStore.create(it) }
                }
            }
    }
}