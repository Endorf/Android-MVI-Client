package com.mvi.sharednotes.login.view.components.middleware

import androidx.core.util.PatternsCompat
import com.mvi.sharednotes.login.view.attributes.Event
import com.mvi.sharednotes.login.view.attributes.Action
import com.mvi.sharednotes.login.data.FakeRepository
import com.mvi.sharednotes.login.view.attributes.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class Middleware(
    private val repository: FakeRepository = FakeRepository(),
    private val loggerMiddleware: LoggerMiddleware = LoggerMiddleware()
) {

    fun dispatch(state: State, event: Event): Flow<Action> = when (event) {
        is Event.Login -> login(state)
        is Event.EmailUpdate -> updateInputState(event.email)
    }

    private fun updateInputState(email: String) = flow {
        emitAction(Action.Input(email))
    }

    private fun login(state: State) = flow {
        if (validateEmail(state.email)) {
            emitAction(Action.Loading)

            val data = repository.doAuth(state.email)
            emitAction(Action.SignedIn(data))
        } else {
            emitAction(Action.Error)
        }
    }

    private suspend fun FlowCollector<Action>.emitAction(action: Action) {
        loggerMiddleware.dispatch(action)

        emit(action)
    }

    companion object {
        private fun validateEmail(email: String): Boolean =
            PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}