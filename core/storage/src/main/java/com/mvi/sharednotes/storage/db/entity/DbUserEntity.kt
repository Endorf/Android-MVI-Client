package com.mvi.sharednotes.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DbUserEntity(
    @PrimaryKey
    @ColumnInfo(name = "row_id")
    val userId: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "username") val userName: String?,
    @ColumnInfo(name = "name") val name: String?,
    @Embedded val address: DbAddressEmbeddedEntity? = null
)