package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.notes.entity.Notes
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun get(): Flow<List<Notes>>
}