package com.mvi.sharednotes.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "row_id")
    val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "username") val userName: String?,
    @ColumnInfo(name = "name") val name: String?,
    @Embedded val address: AddressEmbeddedEntity? = null
)