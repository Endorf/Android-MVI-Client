package com.mvi.sharednotes.storage.db

import com.mvi.sharednotes.storage.NoteDataStore
import com.mvi.sharednotes.storage.db.dao.NoteDao
import com.mvi.sharednotes.storage.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalNoteDataStore @Inject constructor(
    private val dao: NoteDao
) : NoteDataStore {

    override fun get(): Flow<NoteEntity> = dao.read()

    override fun create(note: NoteEntity): Unit = dao.insert(note)

    override fun create(notes: List<NoteEntity>): Unit = dao.insert(notes)
}