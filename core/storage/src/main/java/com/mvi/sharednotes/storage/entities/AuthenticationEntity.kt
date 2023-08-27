package com.mvi.sharednotes.storage.entities

import java.util.concurrent.TimeUnit

data class AuthenticationEntity(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshExpiresIn: Int
) {

    val expiresInMillis: Long
        get() {
            return TimeUnit.SECONDS.toMillis(expiresIn.toLong())
        }

    val refreshExpiresInInMillis: Long
        get() {
            return TimeUnit.SECONDS.toMillis(refreshExpiresIn.toLong())
        }
}