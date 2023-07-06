package com.mvi.sharednotes.storage.di

import android.content.Context
import androidx.room.Room
import com.mvi.sharednotes.storage.NoteDataStore
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.db.LocalNoteDataStore
import com.mvi.sharednotes.storage.db.LocalUserDataStore
import com.mvi.sharednotes.storage.db.MIGRATION_2_3
import com.mvi.sharednotes.storage.db.SharedNotesDatabase
import com.mvi.sharednotes.storage.db.dao.NoteDao
import com.mvi.sharednotes.storage.db.dao.UserDao
import com.mvi.sharednotes.storage.di.qualifier.Local
import com.mvi.sharednotes.storage.di.qualifier.Shared
import com.mvi.sharednotes.storage.preferences.SharedUserDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Shared
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): UserDataStore = SharedUserDataStore(context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SharedNotesDatabase {
        return Room.databaseBuilder(
            context,
            SharedNotesDatabase::class.java,
            SharedNotesDatabase.DB_NAME
        )
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: SharedNotesDatabase): UserDao = appDatabase.userDao()

    @Provides
    @Local
    fun provideUserLocalDataStore(userDao: UserDao): UserDataStore = LocalUserDataStore(userDao)

    @Provides
    fun provideNoteDao(appDatabase: SharedNotesDatabase): NoteDao = appDatabase.noteDao()

    @Provides
    fun provideNoteLocalDataStore(noteDao: NoteDao): NoteDataStore = LocalNoteDataStore(noteDao)
}