package com.mvi.sharednotes.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.sharednotes.data.Repository
import com.mvi.sharednotes.view.attributes.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {

    val state: StateFlow<State> = repository.get().map { user ->
        State.Initialized(user != null && user.userId > 0)
    }.stateIn(
        scope = viewModelScope,
        initialValue = State.Loading,
        started = SharingStarted.WhileSubscribed(TIMEOUT)
    )

    companion object {
        private const val TIMEOUT = 5_000L
    }
}