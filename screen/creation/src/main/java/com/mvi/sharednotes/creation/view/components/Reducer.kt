package com.mvi.sharednotes.creation.view.components

import com.mvi.sharednotes.creation.view.attributes.Action
import com.mvi.sharednotes.creation.view.attributes.Effect
import com.mvi.sharednotes.creation.view.attributes.State
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class Reducer @Inject constructor() {

    fun sendEvent(
        state: MutableStateFlow<State>,
        effect: MutableStateFlow<Effect>,
        action: Action
    ) {
        reduce(state, effect, action)
    }

    private fun reduce(
        state: MutableStateFlow<State>,
        effect: MutableStateFlow<Effect>,
        action: Action
    ) {
        when (action) {
            Action.Loading -> state.value = state.value.copy(
                isLoading = true,
                isDropDownError = false,
                isTitleError = false
            )

            is Action.Submit -> {
                state.value = state.value.copy(
                    isLoading = false,
                    isDropDownError = false,
                    isTitleError = false,
                    isSubmitted = true
                )
                effect.value = effect.value.copy(transitionNotes = true)
            }

            is Action.TitleError -> state.value = state.value.copy(
                isLoading = false,
                isDropDownError = false,
                isTitleError = true,
                errorMessage = action.e?.message ?: ""
            )
            is Action.TagError -> state.value = state.value.copy(
                isLoading = false,
                isDropDownError = true,
                isTitleError = false,
                errorMessage = action.e?.message ?: ""
            )

            is Action.TitleInput -> state.value = state.value.copy(
                title = action.userInput,
                isDropDownError = false,
                isTitleError = false
            )

            is Action.DescriptionInput -> state.value = state.value.copy(
                description = action.userInput,
                isDropDownError = false,
                isTitleError = false
            )

            is Action.TagInput -> state.value = state.value.copy(
                tag = action.userInput,
                isDropDownError = false,
                isTitleError = false
            )
        }
    }
}