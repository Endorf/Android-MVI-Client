package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.notes.entity.Notes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesRepository @Inject constructor() : Repository {

    override suspend fun get(): Flow<List<Notes>> = flow {
        emit(getMockedList())
    }

    // todo: upload list from network. store it
    companion object {
        private fun getMockedList(): List<Notes> {
            return mutableListOf(
                Notes(0, "Tom", "tom", "https://url.com", "title", "description"),
                Notes(1, "Jack", "jack", "https://url.com", "title", "description"),
                Notes(2, "Mac", "mac", "https://url.com", "title", "description"),
                Notes(3, "Jul", "jul", "https://url.com", "title", "description"),
                Notes(4, "Mike", "mike", "https://url.com", "title", "description"),
                Notes(5, "Rosy", "rosy", "https://url.com", "title", "description"),
                Notes(6, "Ross", "ross", "https://url.com", "title", "description"),
                Notes(7, "Pedro", "pedro", "https://url.com", "title", "description"),
                Notes(15, "Rosy1", "rosy1", "https://url.com", "title", "description"),
                Notes(16, "Ross1", "ross1", "https://url.com", "title", "description"),
                Notes(17, "Pedro1", "pedro1", "https://url.com", "title", "description"),
            )
        }
    }
}