package com.mvi.sharednotes.login.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.sharednotes.login.view.attributes.Effect
import com.mvi.sharednotes.login.view.attributes.Event
import com.mvi.sharednotes.login.view.attributes.State
import com.mvi.sharednotes.login.view.components.middleware.Middleware
import com.mvi.sharednotes.login.view.components.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val reducer: Reducer,
    private val middleware: Middleware
) : ViewModel() {

    private val _effect: MutableStateFlow<Effect> = MutableStateFlow(Effect.create())
    val effect: StateFlow<Effect>
        get() = _effect

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.create())
    val state: StateFlow<State>
        get() = _state

    fun dispatch(event: Event) {
        viewModelScope.launch {
            middleware.dispatch(_state.value, event)
                .onEach {
                    reducer.sendEvent(_state, _effect, it)
                }.collect()
        }
    }
}