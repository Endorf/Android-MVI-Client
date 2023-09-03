package com.mvi.sharednotes.view.attributes

sealed interface Effect {

    object None : Effect

    object ExitTransition : Effect
}