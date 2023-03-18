package com.mvi.sharednotes.login.view.attributes

data class Effect(
    val transitionNotes: Boolean,
) {

    companion object {
        fun create() = Effect(false)
    }
}