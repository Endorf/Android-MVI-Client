package com.mvi.sharednotes.storage.di

import android.app.Application
import androidx.room.Room
import com.mvi.sharednotes.storage.NoteDataStore
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.db.LocalNoteDataStore
import com.mvi.sharednotes.storage.db.LocalUserDataStore
import com.mvi.sharednotes.storage.db.SharedNotesDatabase
import com.mvi.sharednotes.storage.db.dao.NoteDao
import com.mvi.sharednotes.storage.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): SharedNotesDatabase {
        return Room.databaseBuilder(
            context,
            SharedNotesDatabase::class.java,
            SharedNotesDatabase.DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: SharedNotesDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideUserLocalDataStore(userDao: UserDao): UserDataStore = LocalUserDataStore(userDao)

    @Provides
    fun provideNoteLocalDataStore(noteDao: NoteDao): NoteDataStore = LocalNoteDataStore(noteDao)
}