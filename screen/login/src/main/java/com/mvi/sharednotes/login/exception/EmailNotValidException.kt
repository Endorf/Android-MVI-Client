package com.mvi.sharednotes.login.exception

class EmailNotValidException : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "Email is not valid"
    }
}