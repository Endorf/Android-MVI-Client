package com.mvi.sharednotes.notes.view.components

import com.mvi.sharednotes.notes.attributes.Action
import com.mvi.sharednotes.notes.attributes.Event
import com.mvi.sharednotes.notes.attributes.State
import com.mvi.sharednotes.notes.data.Repository
import com.mvi.sharednotes.notes.data.mappers.toNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Middleware @Inject constructor(
    private val repository: Repository,
) {

    suspend fun dispatch(state: State, event: Event): Flow<Action> = when (event) {
        is Event.GetNotes -> getNotes(state)
    }

    private suspend fun getNotes(state: State): Flow<Action> {
        return flow {
            emit(Action.Loading)

            repository.get().map { notes ->
                notes.map { it.toNotes() }
            }.collect { notes ->
                emit(Action.ShowNotes(notes))
            }
        }
    }
}