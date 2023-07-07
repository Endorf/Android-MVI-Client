package com.mvi.sharednotes.creation.view.attributes

sealed interface Action {

    object Loading : Action

    data class TitleInput(val userInput: String) : Action

    data class DescriptionInput(val userInput: String) : Action

    data class TagInput(val userInput: String) : Action

    data class TitleError(val e: Throwable? = null) : Action

    data class TagError(val e: Throwable? = null) : Action

    object Submit : Action
}