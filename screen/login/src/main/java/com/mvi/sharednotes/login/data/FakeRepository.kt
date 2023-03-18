package com.mvi.sharednotes.login.data

import kotlinx.coroutines.delay

class FakeRepository {

    suspend fun doAuth(email: String?): String? {
        delay(3000)
        return email
    }
}