package com.mvi.sharednotes.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvi.sharednotes.storage.db.entity.DbNoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun read(): List<DbNoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: DbNoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: List<DbNoteEntity>)
}