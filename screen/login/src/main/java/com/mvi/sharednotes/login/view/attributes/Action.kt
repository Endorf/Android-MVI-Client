package com.mvi.sharednotes.login.view.attributes

sealed interface Action {

    object Loading : Action

    data class Input(val userInput: String) : Action

    data class Error(val e: Throwable? = null) : Action

    data class SignedIn(
        val user: String?
    ) : Action
}