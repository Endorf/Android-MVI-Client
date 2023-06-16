package com.mvi.sharednotes.login.data.entity.mapper

import com.mvi.sharednotes.storage.db.entity.UserEntity

fun UserEntity.toUserEntity() =
    com.mvi.sharednotes.network.data.api.user.entity.UserEntity(
        userId,
        email,
        userName,
        name
    )

fun com.mvi.sharednotes.network.data.api.user.entity.UserEntity.toUserEntity() =
    UserEntity(
        id,
        email,
        userName,
        name
    )