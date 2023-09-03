package com.mvi.sharednotes.view.attributes

sealed interface Event {

    object Logout : Event
}