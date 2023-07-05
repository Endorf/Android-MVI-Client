package com.mvi.sharednotes.storage.entities.mapper

import com.mvi.sharednotes.storage.db.entity.DbUserEntity
import com.mvi.sharednotes.storage.entities.LocalUserEntity

fun LocalUserEntity.toDbUserEntity() = DbUserEntity(
    userId,
    email,
    userName,
    name
)

fun DbUserEntity.toLocalUserEntity() = LocalUserEntity(
    userId,
    email,
    userName,
    name
)