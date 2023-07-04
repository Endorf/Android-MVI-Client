package com.mvi.sharednotes.creation.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class NoteRepository @Inject constructor() : Repository {

    override suspend fun create(title: String, description: String) {
        delay(1000)
    }
}