package com.mvi.sharednotes.di

import com.mvi.sharednotes.data.InitializationRepository
import com.mvi.sharednotes.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface InitializationDataModule {

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: InitializationRepository): Repository
}