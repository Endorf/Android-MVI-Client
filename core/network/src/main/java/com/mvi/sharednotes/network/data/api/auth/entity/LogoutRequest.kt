package com.mvi.sharednotes.network.data.api.auth.entity

import com.google.gson.annotations.SerializedName

class LogoutRequest(
    @SerializedName("refresh_token") val token: String
)