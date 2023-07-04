package com.mvi.sharednotes.notes.attributes

import com.mvi.sharednotes.notes.view.entity.Note

sealed interface Action {

    object Loading : Action
    object Refreshing : Action

    data class Error(val e: Throwable? = null) : Action

    data class ShowNotes(
        val notes: List<Note>
    ) : Action
}