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
                hasError = false
            )

            is Action.Submit -> {
                state.value = state.value.copy(
                    isLoading = false,
                    hasError = false,
                    isSubmited = true
                )
                effect.value = effect.value.copy(transitionNotes = true)
            }

            is Action.Error -> state.value = state.value.copy(
                isLoading = false,
                hasError = true,
                errorMessage = action.e?.message ?: ""
            )

            is Action.TitleInput -> state.value = state.value.copy(
                title = action.userInput,
                hasError = false
            )

            is Action.DescriptionInput -> state.value = state.value.copy(
                description = action.userInput,
                hasError = false
            )
        }
    }
}