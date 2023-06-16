package com.mvi.sharednotes.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mvi.sharednotes.storage.BuildConfig
import com.mvi.sharednotes.storage.db.dao.NoteDao
import com.mvi.sharednotes.storage.db.dao.UserDao
import com.mvi.sharednotes.storage.db.entity.NoteEntity
import com.mvi.sharednotes.storage.db.entity.UserEntity

@Database(
    version = BuildConfig.DB_VERSION,
    entities = [UserEntity::class, NoteEntity::class],
    exportSchema = true
)
abstract class SharedNotesDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun noteDao(): NoteDao

    companion object {
        internal const val DB_NAME = BuildConfig.DB_NAME
    }
}

val MIGRATION_2_3 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `note` (`row_id` INTEGER NOT NULL, " +
                "`username` TEXT NOT NULL, " +
                "`tag` TEXT NOT NULL, " +
                "`title` TEXT, " +
                "`body` TEXT, PRIMARY KEY(`row_id`))"
        )
    }
}