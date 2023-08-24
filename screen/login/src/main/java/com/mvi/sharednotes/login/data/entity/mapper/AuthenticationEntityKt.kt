package com.mvi.sharednotes.login.data.entity.mapper

import com.mvi.sharednotes.network.data.api.auth.entity.AuthenticationResponse
import com.mvi.sharednotes.storage.entities.AuthenticationEntity

fun AuthenticationResponse.toLocalEntity() = AuthenticationEntity(
        access_token,
        expires_in,
        refresh_token,
        refresh_expires_in
    )