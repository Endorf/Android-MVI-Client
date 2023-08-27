package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.post.PostApi
import com.mvi.sharednotes.network.data.api.post.entity.RemotePostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemotePostDataStoreImpl @Inject constructor(
    private val api: PostApi
) : RemotePostDataStore {

    override suspend fun read(): Flow<List<RemotePostEntity>> = flow {
        val posts = api.get()
        emit(posts)
    }

    override suspend fun read(id: Long): Flow<RemotePostEntity> = flow {
        val post = api.get(id)
        emit(post)
    }
}