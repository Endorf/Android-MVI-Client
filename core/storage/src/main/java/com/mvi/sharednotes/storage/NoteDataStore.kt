package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteDataStore {

    fun get(note: NoteEntity): Flow<NoteEntity>

    fun create(note: NoteEntity)

    fun create(notes: List<NoteEntity>)
}