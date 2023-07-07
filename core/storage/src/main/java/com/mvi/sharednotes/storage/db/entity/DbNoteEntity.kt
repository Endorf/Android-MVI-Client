package com.mvi.sharednotes.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class DbNoteEntity(
    val postId: Int,
    val username: String,
    val tag: String,
    val title: String?,
    val body: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "row_id")
    val id: Int = 0,
)