package com.mvi.sharednotes.login.data.entity.mapper

import com.mvi.sharednotes.login.view.entity.UserCredentials
import com.mvi.sharednotes.network.data.api.user.entity.UserEntity

fun UserCredentials.toUserEntity() = UserEntity(email = email)