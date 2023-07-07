package com.mvi.sharednotes.creation.di

import com.mvi.sharednotes.creation.data.Repository
import com.mvi.sharednotes.creation.view.components.Middleware
import com.mvi.sharednotes.creation.view.components.Reducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NoteViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindMainMiddleware(repository: Repository) = Middleware(repository)

    @Provides
    @ViewModelScoped
    fun bindReducer() = Reducer()
}