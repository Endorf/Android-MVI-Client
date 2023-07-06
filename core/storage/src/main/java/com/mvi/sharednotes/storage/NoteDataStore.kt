package com.mvi.sharednotes.storage

import com.mvi.sharednotes.storage.entities.LocalPostEntity

interface NoteDataStore {

    fun get(): List<LocalPostEntity>

    fun create(note: LocalPostEntity)

    fun create(notes: List<LocalPostEntity>)
}