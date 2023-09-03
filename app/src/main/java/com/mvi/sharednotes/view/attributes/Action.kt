package com.mvi.sharednotes.view.attributes

sealed interface Action {

    object Loading : Action

    object Error : Action

    object Exit : Action
}