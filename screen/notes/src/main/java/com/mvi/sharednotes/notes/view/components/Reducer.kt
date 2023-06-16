package com.mvi.sharednotes.notes.view.components

import com.mvi.sharednotes.notes.attributes.Action
import com.mvi.sharednotes.notes.attributes.Effect
import com.mvi.sharednotes.notes.attributes.State
import kotlinx.coroutines.flow.MutableStateFlow

class Reducer {

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
                hasError = false,
                errorMessage = ""
            )

            is Action.Error -> state.value = state.value.copy(
                isLoading = false,
                hasError = true,
                errorMessage = action.e?.message ?: ""
            )

            is Action.ShowNotes -> state.value = state.value.copy(
                isLoading = false,
                hasError = false,
                errorMessage = "",
                action.notes
            )
        }
    }
}