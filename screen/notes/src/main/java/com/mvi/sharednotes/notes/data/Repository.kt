package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun get(): Flow<List<PostEntity>>

    suspend fun get(id: Long): Flow<PostEntity>
}