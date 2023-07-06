package com.mvi.sharednotes.storage.db

import com.mvi.sharednotes.storage.NoteDataStore
import com.mvi.sharednotes.storage.db.dao.NoteDao
import com.mvi.sharednotes.storage.entities.LocalPostEntity
import com.mvi.sharednotes.storage.entities.mapper.toDbPostEntity
import com.mvi.sharednotes.storage.entities.mapper.toDbPostEntityList
import com.mvi.sharednotes.storage.entities.mapper.toLocalPostEntityList
import javax.inject.Inject

class LocalNoteDataStore @Inject constructor(
    private val dao: NoteDao
) : NoteDataStore {

    override fun get(): List<LocalPostEntity> = dao.read().toLocalPostEntityList()

    override fun create(note: LocalPostEntity) = note.toDbPostEntity().let {
        dao.insert(it)
    }

    override fun create(notes: List<LocalPostEntity>): Unit =
        notes.toDbPostEntityList().let { dao.insert(it) }
}