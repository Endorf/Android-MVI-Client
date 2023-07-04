package com.mvi.sharednotes.creation.view.attributes

data class State(
    val isLoading: Boolean,
    val hasError: Boolean,
    val isSubmited: Boolean,
    val title: String,
    val description: String,
    val errorMessage: String
) {
    companion object {
        fun create() =
            State(
                isLoading = false,
                hasError = false,
                isSubmited = false,
                title = "",
                description = "",
                errorMessage = ""
            )
    }
}