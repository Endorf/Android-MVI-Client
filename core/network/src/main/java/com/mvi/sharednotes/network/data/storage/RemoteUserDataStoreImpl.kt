package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.user.UserApi
import com.mvi.sharednotes.network.data.api.user.entity.RemoteUserEntity
import javax.inject.Inject

class RemoteUserDataStoreImpl @Inject constructor(
    private val api: UserApi
) : RemoteUserDataStore {

    override suspend fun create(user: RemoteUserEntity) = api.create(user)

    override suspend fun read(user: RemoteUserEntity) = api.get(user.id)
}