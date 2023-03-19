package com.mvi.sharednotes.login.di

import com.mvi.sharednotes.login.data.LoginRepository
import com.mvi.sharednotes.login.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface LoginDataModule {

    @Binds
    @ViewModelScoped
    fun bindRepository(repository: LoginRepository): Repository
}