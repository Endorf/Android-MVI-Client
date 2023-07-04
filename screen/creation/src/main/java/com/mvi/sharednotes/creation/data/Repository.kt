package com.mvi.sharednotes.creation.data

interface Repository {

    suspend fun create(title: String, description: String)
}