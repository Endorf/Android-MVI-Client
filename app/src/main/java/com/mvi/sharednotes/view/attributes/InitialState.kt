package com.mvi.sharednotes.view.attributes

sealed interface InitialState {

    object Loading : InitialState

    object Initialized : InitialState
}