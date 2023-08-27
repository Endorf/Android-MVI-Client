package com.mvi.sharednotes.network.data.utils.network

import okhttp3.MediaType.Companion.toMediaType

internal enum class HttpBodyFormat {
    JSON,
    X_WWW_FORM_URLENCODED;

    companion object {

        fun HttpBodyFormat.toMediaType() = when (this) {
            JSON -> "application/json"
            X_WWW_FORM_URLENCODED -> "application/x-www-form-urlencoded"
        }.toMediaType()
    }
}