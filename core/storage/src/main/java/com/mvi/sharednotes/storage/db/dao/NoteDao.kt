package com.mvi.sharednotes.storage.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvi.sharednotes.storage.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteDao {

    @Query("SELECT * FROM note")
    fun read(): Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: List<NoteEntity>)
}