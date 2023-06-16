package com.mvi.sharednotes.notes.attributes

class Effect(
    val transitionNotes: Boolean
) {

    companion object {
        fun create() = Effect(false)
    }
}