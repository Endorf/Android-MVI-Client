package com.mvi.sharednotes.storage.entities

data class AuthenticationEntity(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val refresh_expires_in: Int
) {

    val expiresInMillis: Long
        get() {
            return expires_in.toLong() * 1000
        }

    val refreshExpiresInInMillis: Long
        get() {
            return refresh_expires_in.toLong() * 1000
        }
}