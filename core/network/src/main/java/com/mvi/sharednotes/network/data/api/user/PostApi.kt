package com.mvi.sharednotes.network.data.api.user

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun get(): List<PostEntity>

    @GET("posts/{id}")
    suspend fun get(@Path("id") id: Long): PostEntity
}