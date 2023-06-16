package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.db.entity.NoteEntity

interface NoteDataStore {

    fun get(): List<NoteEntity>

    fun create(note: NoteEntity)

    fun create(notes: List<NoteEntity>)
}