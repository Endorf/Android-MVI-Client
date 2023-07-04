package com.mvi.sharednotes.creation.view.components

import com.mvi.sharednotes.creation.data.Repository
import com.mvi.sharednotes.creation.view.attributes.Action
import com.mvi.sharednotes.creation.view.attributes.Event
import com.mvi.sharednotes.creation.view.attributes.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Middleware @Inject constructor(
    private val repository: Repository
) {

    suspend fun dispatch(state: State, event: Event): Flow<Action> = when (event) {
        is Event.Submit -> submit(state)
        is Event.TitleUpdate -> updateTitleUpdateState(event.title)
        is Event.DescriptionUpdate -> updateDescriptionUpdateState(event.description)
    }

    private fun submit(state: State) = flow {
        emit(Action.Loading)
        repository.create(state.title, state.description)
        emit(Action.Submit)
    }

    private fun updateDescriptionUpdateState(description: String) = flow {
        emit(Action.DescriptionInput(description))
    }

    private fun updateTitleUpdateState(email: String) = flow {
        emit(Action.TitleInput(email))
    }
}