package com.mvi.sharednotes.notes.di

import com.mvi.sharednotes.notes.data.NotesRepository
import com.mvi.sharednotes.notes.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface NotesDataModule {
    @Binds
    @ViewModelScoped
    fun bindRepository(repository: NotesRepository): Repository
}