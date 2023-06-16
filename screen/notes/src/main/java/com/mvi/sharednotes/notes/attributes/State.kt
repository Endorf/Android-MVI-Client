package com.mvi.sharednotes.notes.attributes

import com.mvi.sharednotes.notes.view.entity.Note

data class State(
    val isLoading: Boolean,
    val hasError: Boolean,
    val errorMessage: String,
    val notes:List<Note>
) {
    companion object {
        fun create() = State(
            isLoading = false,
            hasError = false,
            errorMessage = "",
            notes = emptyList()
        )
    }
}