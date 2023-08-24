package com.mvi.sharednotes.login.exception

class AuthorizationException(val code: Int = 401) : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "User not authorized"
    }
}