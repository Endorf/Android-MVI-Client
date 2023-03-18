package com.mvi.sharednotes.login.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeRepository @Inject constructor() {

    suspend fun doAuth(email: String?): String? {
        delay(DELAY)
        return email
    }

    companion object {
        private const val DELAY = 3000L
    }
}