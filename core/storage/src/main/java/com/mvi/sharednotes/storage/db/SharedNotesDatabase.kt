package com.mvi.sharednotes.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvi.sharednotes.storage.BuildConfig
import com.mvi.sharednotes.storage.db.dao.UserDao
import com.mvi.sharednotes.storage.db.entity.UserEntity

@Database(
    version = BuildConfig.DB_VERSION,
    entities = [UserEntity::class],
    exportSchema = true
)
abstract class SharedNotesDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        internal const val DB_NAME = BuildConfig.DB_NAME
    }
}