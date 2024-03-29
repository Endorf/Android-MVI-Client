package com.mvi.sharednotes.network.data.api.auth

import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationRequest
import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.network.data.api.auth.entity.LogoutRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/signin")
    @Headers("Content-Type: application/json")
    suspend fun signIn(@Body user: AuthenticationRequest): Response<AuthenticationResponse>

    @POST("auth/logout")
    @Headers("Content-Type: application/json")
    suspend fun logout(@Body body: LogoutRequest): Response<*>
}