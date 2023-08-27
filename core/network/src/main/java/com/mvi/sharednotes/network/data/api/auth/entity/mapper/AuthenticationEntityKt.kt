package com.mvi.sharednotes.network.data.api.auth.entity.mapper

import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.storage.entities.AuthenticationEntity

fun AuthenticationResponse.toLocalEntity() = AuthenticationEntity(
    accessToken,
    expiresIn,
    refreshToken,
    refreshExpiresIn
)