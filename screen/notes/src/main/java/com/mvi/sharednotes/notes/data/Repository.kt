package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.notes.view.entity.Note
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun get(): Flow<List<Note>>

    suspend fun get(id: Long): Flow<Note>
}