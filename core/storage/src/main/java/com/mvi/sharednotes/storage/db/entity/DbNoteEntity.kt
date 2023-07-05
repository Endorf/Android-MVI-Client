package com.mvi.sharednotes.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class DbNoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "row_id")
    val postId: Int,
    val username: String,
    val tag: String,
    val title: String?,
    val body: String?
)