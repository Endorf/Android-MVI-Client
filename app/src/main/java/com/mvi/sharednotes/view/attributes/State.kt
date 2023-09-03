package com.mvi.sharednotes.view.attributes

data class State(
    val isLoading: Boolean,
    val hasError: Boolean,
    val isUserAuthenticated: Boolean,
) {
    companion object {
        fun create() =
            State(
                isLoading = false,
                hasError = false,
                isUserAuthenticated = false
            )
    }
}