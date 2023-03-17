package com.mvi.sharednotes.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.sharednotes.view.attributes.InitialState
import kotlinx.coroutines.flow.*

class InitialViewModel : ViewModel() {

    val state = initialize().stateIn(
        scope = viewModelScope,
        initialValue = InitialState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    // todo: replace with initialization from Repository
    private fun initialize() = flowOf(InitialState.Initialized)
}