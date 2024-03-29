package com.mvi.sharednotes.notes.view.components

import com.mvi.sharednotes.notes.attributes.Action
import com.mvi.sharednotes.notes.attributes.Effect
import com.mvi.sharednotes.notes.attributes.State
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

    @Suppress("UnusedPrivateMember")
    private fun reduce(
        state: MutableStateFlow<State>,
        effect: MutableStateFlow<Effect>,
        action: Action
    ) {
        when (action) {
            Action.Refreshing -> state.value = state.value.copy(
                isRefreshing = true,
                isLoading = false,
                hasError = false,
                errorMessage = ""
            )
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
                isRefreshing = false,
                hasError = false,
                errorMessage = "",
                action.notes
            )
        }
    }
}