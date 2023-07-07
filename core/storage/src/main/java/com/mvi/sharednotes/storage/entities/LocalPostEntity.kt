package com.mvi.sharednotes.storage.entities

data class LocalPostEntity(
    val postId: Int,
    val username: String,
    val tag: String,
    val title: String?,
    val description: String?
) {
    companion object {
        const val UNKNOWN = "Unknown"
    }
}