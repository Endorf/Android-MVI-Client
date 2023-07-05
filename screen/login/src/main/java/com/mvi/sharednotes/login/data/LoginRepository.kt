package com.mvi.sharednotes.login.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.data.entity.mapper.toLocalUserEntity
import com.mvi.sharednotes.login.data.entity.mapper.toRemoteUserEntity
import com.mvi.sharednotes.login.data.entity.mapper.toUser
import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvi.sharednotes.storage.db.LocalUserDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataStore: RemoteUserDataStore,
    private val localDataStore: LocalUserDataStore
) : Repository {

    override suspend fun get(credentials: UserCredentials): Flow<User> = flow {
        // todo: replace with update

        val user = remoteDataStore.read(credentials.toRemoteUserEntity())
        localDataStore.create(user.toLocalUserEntity())
        emit(user.toUser())
    }

    override suspend fun create(credentials: UserCredentials): Flow<User> = flow {
        val user = remoteDataStore.create(credentials.toRemoteUserEntity())
        localDataStore.create(user.toLocalUserEntity())
        emit(user.toUser())
    }
}