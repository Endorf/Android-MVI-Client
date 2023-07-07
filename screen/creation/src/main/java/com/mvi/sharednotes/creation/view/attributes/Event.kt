package com.mvi.sharednotes.creation.view.attributes

sealed interface Event {

    object Submit : Event

    data class TitleUpdate(val title: String = "") : Event

    data class DescriptionUpdate(val description: String = "") : Event

    data class TagUpdate(val tag: String = "") : Event
}