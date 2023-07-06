package com.mvi.sharednotes.view.attributes

sealed interface State {

    object Loading : State

    data class Initialized(val isUserAuthenticated: Boolean = false) : State
}