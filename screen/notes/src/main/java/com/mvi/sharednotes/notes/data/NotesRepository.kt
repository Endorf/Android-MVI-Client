package com.mvi.sharednotes.notes.data

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import com.mvi.sharednotes.network.data.storage.RemotePostDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val remoteDataStore: RemotePostDataStore
) : Repository {

    override suspend fun get(): Flow<List<PostEntity>> = remoteDataStore.read()

    override suspend fun get(id: Long): Flow<PostEntity> = remoteDataStore.read(id)
}