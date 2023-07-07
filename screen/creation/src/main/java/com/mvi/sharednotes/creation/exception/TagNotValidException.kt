package com.mvi.sharednotes.creation.exception

class TagNotValidException : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "Tag is required"
    }
}