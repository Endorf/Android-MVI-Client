package com.mvi.sharednotes.network.data.api.auth.entity

data class AuthenticationResponse(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val refresh_expires_in: Int
)