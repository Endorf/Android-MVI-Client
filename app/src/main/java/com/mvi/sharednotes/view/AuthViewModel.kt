package com.mvi.sharednotes.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.sharednotes.view.attributes.Effect
import com.mvi.sharednotes.view.attributes.Event
import com.mvi.sharednotes.view.attributes.State
import com.mvi.sharednotes.view.components.Reducer
import com.mvi.sharednotes.view.components.middleware.Middleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val reducer: Reducer,
    private val middleware: Middleware
) : ViewModel() {

    private val _effect: MutableStateFlow<Effect> = MutableStateFlow(Effect.None)
    val effect: StateFlow<Effect>
        get() = _effect

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.create())
    val state: StateFlow<State>
        get() = _state

    fun dispatch(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            middleware.dispatch(event)
                .onEach {
                    reducer.sendEvent(_state, _effect, it)
                }.collect()
        }
    }
}