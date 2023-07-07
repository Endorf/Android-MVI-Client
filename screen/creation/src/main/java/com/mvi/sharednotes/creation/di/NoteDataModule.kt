package com.mvi.sharednotes.creation.di

import com.mvi.sharednotes.creation.data.NoteRepository
import com.mvi.sharednotes.creation.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface NoteDataModule {

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: NoteRepository): Repository
}