package com.mvi.sharednotes.storage.entities

data class LocalUserEntity(
    val userId: Int = -1,
    val email: String = "",
    val userName: String? = null,
    val name: String? = null
)