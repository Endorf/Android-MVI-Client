package com.mvi.sharednotes.storage.db

import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.db.dao.UserDao
import com.mvi.sharednotes.storage.entities.LocalUserEntity
import com.mvi.sharednotes.storage.entities.mapper.toLocalUserEntity
import com.mvi.sharednotes.storage.entities.mapper.toDbUserEntity
import javax.inject.Inject

class LocalUserDataStore @Inject constructor(
    private val userDao: UserDao
) : UserDataStore {

    override suspend fun clean() {
        TODO("Not yet implemented")
    }

    override suspend fun put(user: LocalUserEntity) = user.toDbUserEntity().let {
        userDao.insert(it)
    }

    override suspend fun get(user: LocalUserEntity?) = user?.email?.let {
        userDao.read(user.email)
    }?.toLocalUserEntity()
}