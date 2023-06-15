package com.mvi.sharednotes.notes.attributes

import com.mvi.sharednotes.notes.entity.Notes

sealed interface Action {

    object Loading : Action

    data class Error(val e: Throwable? = null) : Action

    data class ShowNotes(
        val notes: List<Notes>
    ) : Action
}