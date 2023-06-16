package com.mvi.sharednotes.notes.attributes

sealed interface Event {
    object GetNotes : Event
}