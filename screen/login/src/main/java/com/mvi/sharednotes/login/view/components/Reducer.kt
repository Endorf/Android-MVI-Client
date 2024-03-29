package com.mvi.sharednotes.login.view.components

import com.mvi.sharednotes.login.view.attributes.Effect
import com.mvi.sharednotes.login.view.attributes.Action
import com.mvi.sharednotes.login.view.attributes.State
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
            is Action.SignedIn -> {
                state.value = state.value.copy(
                    isLoading = false,
                    hasError = false,
                    isSigned = true
                )
                effect.value = effect.value.copy(transitionNotes = true)
            }
            is Action.Error -> state.value = state.value.copy(
                isLoading = false,
                hasError = true,
                errorMessage = action.e?.message ?: ""
            )
            is Action.Input -> state.value = state.value.copy(
                email = action.userInput,
                hasError = false
            )
        }
    }
}