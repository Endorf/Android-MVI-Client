package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.post.entity.RemotePostEntity
import kotlinx.coroutines.flow.Flow

interface RemotePostDataStore {

    suspend fun read(): Flow<List<RemotePostEntity>>

    suspend fun read(id: Long): Flow<RemotePostEntity>
}