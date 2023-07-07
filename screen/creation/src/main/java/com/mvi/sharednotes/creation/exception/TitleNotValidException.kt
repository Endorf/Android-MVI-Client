package com.mvi.sharednotes.creation.exception

class TitleNotValidException : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "Title is required"
    }
}