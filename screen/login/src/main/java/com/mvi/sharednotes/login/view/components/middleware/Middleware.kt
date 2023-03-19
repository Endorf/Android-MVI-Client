package com.mvi.sharednotes.login.view.components.middleware

import androidx.core.util.PatternsCompat
import com.mvi.sharednotes.login.data.Repository
import com.mvi.sharednotes.login.view.attributes.Event
import com.mvi.sharednotes.login.view.attributes.Action
import com.mvi.sharednotes.login.view.attributes.State
import com.mvi.sharednotes.login.view.entity.UserCredentials
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class Middleware @Inject constructor(
    private val repository: Repository,
    private val loggerMiddleware: LoggerMiddleware
) {

    suspend fun dispatch(state: State, event: Event): Flow<Action> = when (event) {
        is Event.Login -> login(state)
        is Event.EmailUpdate -> updateInputState(event.email)
    }

    private fun updateInputState(email: String) = flow {
        emitAction(Action.Input(email))
    }

    private suspend fun login(state: State): Flow<Action> {
        return flow {
            if (validateEmail(state.email).not()) {
                emitAction(Action.Error)
                return@flow
            }
            emitAction(Action.Loading)

            val credentials = UserCredentials(state.email)

            repository.get(credentials)
                .catch {
                    emitAction(Action.Loading)
                    repository.create(credentials)
                        .catch {
                            emitAction(Action.Error)
                        }.collect {
                            emitAction(Action.SignedIn(it.email))
                        }
                }.collect {
                    emitAction(Action.SignedIn(it.email))
                }
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