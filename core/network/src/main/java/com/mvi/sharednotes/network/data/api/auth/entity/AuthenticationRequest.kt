package com.mvi.sharednotes.network.data.api.auth.entity

data class AuthenticationRequest(
    val email: String,
    val password: String
)