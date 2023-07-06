package com.mvi.sharednotes.login.data.entity

data class User(
    val userId: Int = -1,
    val email: String = "",
    val userName: String? = null,
    val name: String? = null
)