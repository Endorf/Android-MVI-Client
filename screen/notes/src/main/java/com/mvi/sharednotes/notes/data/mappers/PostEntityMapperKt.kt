package com.mvi.sharednotes.notes.data.mappers

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import com.mvi.sharednotes.notes.view.entity.Note

fun PostEntity.toNotes(): Note =
    Note(
        id,
        userId,
        "",
        "",
        "",
        title,
        body
    )

fun Note.toPostEntity(): PostEntity =
    PostEntity(
        id,
        userId,
        title,
        description
    )