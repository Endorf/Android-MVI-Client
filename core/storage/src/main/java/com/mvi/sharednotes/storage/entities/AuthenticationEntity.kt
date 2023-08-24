package com.mvi.sharednotes.storage.entities

data class AuthenticationEntity(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val refresh_expires_in: Int
)