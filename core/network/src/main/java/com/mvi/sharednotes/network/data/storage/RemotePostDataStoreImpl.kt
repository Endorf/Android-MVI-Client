package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.user.PostApi
import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemotePostDataStoreImpl @Inject constructor(
    private val api: PostApi
) : RemotePostDataStore {

    override suspend fun read(): Flow<List<PostEntity>> = flow {
        val posts = api.get()
        emit(posts)
    }

    override suspend fun read(id: Long): Flow<PostEntity> = flow {
        val post = api.get(id)
        emit(post)
    }
}