package com.mvi.sharednotes.login.di

import com.mvi.sharednotes.login.data.Repository
import com.mvi.sharednotes.login.view.components.Reducer
import com.mvi.sharednotes.login.view.components.middleware.LoggerMiddleware
import com.mvi.sharednotes.login.view.components.middleware.Middleware
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class LoginViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindMainMiddleware(
        repository: Repository,
        logger: LoggerMiddleware
    ) = Middleware(repository, logger)

    @Provides
    @ViewModelScoped
    fun bindReducer() = Reducer()
}