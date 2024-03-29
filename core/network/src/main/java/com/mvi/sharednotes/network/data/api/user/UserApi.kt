package com.mvi.sharednotes.network.data.api.user

import com.mvi.sharednotes.network.data.api.user.entity.RemoteUserEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun get(@Path("id") id: Int): RemoteUserEntity

    @GET("api/users/me")
    @Headers("Content-Type: application/json")
    suspend fun getCurrentUser(): RemoteUserEntity

    @POST("user")
    suspend fun create(@Body user: RemoteUserEntity): RemoteUserEntity
}