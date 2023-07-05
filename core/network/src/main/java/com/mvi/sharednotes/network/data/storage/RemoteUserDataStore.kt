package com.mvi.sharednotes.network.data.storage

import com.mvi.sharednotes.network.data.api.user.entity.RemoteUserEntity

interface RemoteUserDataStore {

    suspend fun create(user: RemoteUserEntity): RemoteUserEntity

    suspend fun read(user: RemoteUserEntity): RemoteUserEntity
}