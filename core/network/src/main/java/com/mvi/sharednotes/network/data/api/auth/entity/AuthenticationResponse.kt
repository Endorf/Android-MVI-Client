package com.mvi.sharednotes.network.data.api.auth.entity

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("refresh_expires_in") val refreshExpiresIn: Int
)