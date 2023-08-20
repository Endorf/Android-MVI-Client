package com.mvi.sharednotes.network.di

import com.mvi.sharednotes.network.data.api.auth.AuthApi
import com.mvi.sharednotes.network.data.api.post.PostApi
import com.mvi.sharednotes.network.data.api.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)
}