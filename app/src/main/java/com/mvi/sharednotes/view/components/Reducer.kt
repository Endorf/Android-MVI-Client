package com.mvi.sharednotes.view.components

import com.mvi.sharednotes.view.attributes.Action
import com.mvi.sharednotes.view.attributes.Effect
import com.mvi.sharednotes.view.attributes.State
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
            Action.Exit -> effect.value = Effect.ExitTransition
            Action.Error -> state.value = state.value.copy(isLoading = false, hasError = true)
            Action.Loading -> state.value = state.value.copy(isLoading = true, hasError = false)
        }
    }
}