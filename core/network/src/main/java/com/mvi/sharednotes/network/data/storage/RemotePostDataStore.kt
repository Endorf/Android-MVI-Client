package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface RemotePostDataStore {

    suspend fun read(): Flow<List<PostEntity>>

    suspend fun read(id: Long): Flow<PostEntity>
}