package com.mvi.sharednotes.login.view.attributes

data class State(
    val isLoading: Boolean,
    val hasError: Boolean,
    val isSigned: Boolean,
    val email: String
) {
    companion object {
        fun create() = State(isLoading = false, hasError = false, isSigned = false, email = "")
    }
}