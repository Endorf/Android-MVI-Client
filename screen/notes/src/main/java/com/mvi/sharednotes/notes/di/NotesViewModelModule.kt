package com.mvi.sharednotes.notes.di

import com.mvi.sharednotes.notes.data.Repository
import com.mvi.sharednotes.notes.view.components.Middleware
import com.mvi.sharednotes.notes.view.components.Reducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NotesViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindMainMiddleware(repository: Repository) = Middleware(repository)

    @Provides
    @ViewModelScoped
    fun bindReducer() = Reducer()
}