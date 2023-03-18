package com.mvi.sharednotes.login.view.attributes

sealed interface Event {

    object Login : Event

    data class EmailUpdate(val email: String = "") : Event
}