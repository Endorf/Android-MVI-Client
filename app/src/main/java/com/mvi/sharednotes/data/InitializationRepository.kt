package com.mvi.sharednotes.data

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.data.entity.mapper.toUser
import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.di.qualifier.Shared
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InitializationRepository @Inject constructor(
    @Shared private val sharedDataStore: UserDataStore // TODO: check user by using remoteUserDataStore
) : Repository {

    override fun get(): Flow<User?> = flow {
        emit(sharedDataStore.get()?.toUser())
    }
}