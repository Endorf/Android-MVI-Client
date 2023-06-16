package com.mvi.sharednotes.network.di

import com.mvi.sharednotes.network.data.storage.RemotePostDataStore
import com.mvi.sharednotes.network.data.storage.RemotePostDataStoreImpl
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(FragmentComponent::class, ViewModelComponent::class)
interface RemoteDataStoreModule {

    @Binds
    fun provideUserApi(dataStore: RemoteUserDataStoreImpl): RemoteUserDataStore

    @Binds
    fun providePostApi(dataStore: RemotePostDataStoreImpl): RemotePostDataStore
}