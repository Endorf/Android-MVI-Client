package com.mvi.sharednotes.creation.view.attributes

data class Effect(
    val transitionNotes: Boolean
) {

    companion object {
        fun create() = Effect(false)
    }
}