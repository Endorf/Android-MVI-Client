package com.mvi.sharednotes.creation.view.attributes

data class State(
    val isLoading: Boolean,
    val hasError: Boolean,
    val isSigned: Boolean,
    val title: String,
    val description: String,
    val errorMessage: String
) {
    companion object {
        fun create() =
            State(
                isLoading = false,
                hasError = false,
                isSigned = false,
                title = "",
                description = "",
                errorMessage = ""
            )
    }
}