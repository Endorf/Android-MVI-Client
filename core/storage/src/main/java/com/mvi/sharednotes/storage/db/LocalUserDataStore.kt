package com.mvi.sharednotes.storage.db

import com.mvi.sharednotes.storage.UserDataStore
import com.mvi.sharednotes.storage.db.dao.UserDao
import com.mvi.sharednotes.storage.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataStore @Inject constructor(
    private val userDao: UserDao
) : UserDataStore {

    override fun create(user: UserEntity): Unit = userDao.insert(user)

    override fun get(user: UserEntity): Flow<UserEntity> = userDao.read(user.email)

    override fun get(): Flow<UserEntity> {
        TODO("return all users")
    }

    override fun update(user: UserEntity): Unit = userDao.insert(user)
}