package com.mvi.sharednotes.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.sharednotes.view.attributes.InitialState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InitialViewModel : ViewModel() {

    val state = initialize().map {
        InitialState.Initialized
    }.stateIn(
        scope = viewModelScope,
        initialValue = InitialState.Loading,
        started = SharingStarted.WhileSubscribed(TIMEOUT),
    )

    // todo: replace with initialization from Repository
    @Suppress("MagicNumber")
    private fun initialize() = flow {
        delay(1000)
        emit(InitialState.Initialized)
    }

    companion object {
        private const val TIMEOUT = 5_000L
    }
}