package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.network.data.storage.RemotePostDataStore
import com.mvi.sharednotes.notes.data.mappers.toNote
import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.notes.data.mappers.toLocalPostEntity
import com.mvi.sharednotes.notes.data.mappers.toLocalPostEntityList
import com.mvi.sharednotes.notes.data.mappers.toNoteList
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

    override suspend fun get(isRefreshing: Boolean): Flow<List<Note>> {
        val local = localDataStore.get().toNoteList()

        return remoteDataStore.read()
            .map { it.toNoteList() }
            .onStart { if (!isRefreshing) emit(local) }
            .onEach { remoteNotes ->
                remoteNotes.filter { it !in local }
                    .toLocalPostEntityList()
                    .let { localDataStore.create(it) }
            }
    }

    override suspend fun get(id: Long): Flow<Note> {
        val local = localDataStore.get().toNoteList()

        return remoteDataStore.read(id)
            .map { it.toNote() }
            .onEach { remoteNotes ->
                if (remoteNotes !in local) {
                    remoteNotes.toLocalPostEntity().let { localDataStore.create(it) }
                }
            }
    }
}