package com.mvi.sharednotes.login.data.entity.mapper

import com.mvi.sharednotes.login.data.entity.User
import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.user.entity.RemoteUserEntity
import com.mvi.sharednotes.storage.entities.LocalUserEntity

fun UserCredentials.toRemoteUserEntity() = RemoteUserEntity(email = email)

fun RemoteUserEntity.toLocalUserEntity() = LocalUserEntity(id, email, userName, name)

fun RemoteUserEntity.toUser() = User(id, email, userName, name)