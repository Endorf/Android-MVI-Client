package com.mvi.sharednotes.network.data.api.post

import com.mvi.sharednotes.network.data.api.post.entity.RemotePostEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    // TODO: handle new path
    @GET("api/notes")
    suspend fun get(): List<RemotePostEntity>

    @GET("posts/{id}")
    suspend fun get(@Path("id") id: Long): RemotePostEntity
}