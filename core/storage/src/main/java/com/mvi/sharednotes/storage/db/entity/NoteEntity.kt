package com.mvi.sharednotes.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "row_id")
    val id: Int,
    @ColumnInfo(name = "post_id")
    val postId: Long,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    val title: String?,
    val body: String?
)