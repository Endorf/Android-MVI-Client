package com.mvi.sharednotes.network.data.api.user

import com.mvi.sharednotes.network.data.api.user.entity.RemotePostEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun get(): List<RemotePostEntity>

    @GET("posts/{id}")
    suspend fun get(@Path("id") id: Long): RemotePostEntity
}