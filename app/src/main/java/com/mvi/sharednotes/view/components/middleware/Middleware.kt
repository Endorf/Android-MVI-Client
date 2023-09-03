package com.mvi.sharednotes.view.components.middleware

import com.mvi.sharednotes.data.AppRepository
import com.mvi.sharednotes.view.attributes.Action
import com.mvi.sharednotes.view.attributes.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class Middleware @Inject constructor(
    private val repository: AppRepository
) {

    suspend fun dispatch(event: Event): Flow<Action> = when (event) {
        is Event.Logout -> logout()
    }

    private suspend fun logout(): Flow<Action> {
        return flow {
            emit(Action.Loading)

            repository.logout().catch {
                repository.clearUserSpace()
                emit(Action.Exit)
            }.collect {
                if (it) {
                    repository.clearUserSpace()
                    emit(Action.Exit)
                } else {
                    emit(Action.Error)
                }
            }
        }
    }
}