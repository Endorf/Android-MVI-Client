package com.mvi.sharednotes.creation.view.attributes

data class State(
    val isLoading: Boolean,
    val isDropDownError: Boolean,
    val isTitleError: Boolean,
    val isSubmitted: Boolean,
    val title: String,
    val description: String,
    val tag: String,
    val errorMessage: String
) {

    companion object {
        fun create() =
            State(
                isLoading = false,
                isDropDownError = false,
                isTitleError = false,
                isSubmitted = false,
                title = "",
                description = "",
                tag = "",
                errorMessage = ""
            )
    }
}