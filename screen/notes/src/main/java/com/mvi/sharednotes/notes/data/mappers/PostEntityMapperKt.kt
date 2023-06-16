package com.mvi.sharednotes.notes.data.mappers

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import com.mvi.sharednotes.notes.view.entity.Notes

fun PostEntity.toNotes(): Notes =
    Notes(
        id,
        userId,
        "",
        "",
        "",
        title,
        body
    )

fun Notes.toPostEntity(): PostEntity =
    PostEntity(
        id,
        userId,
        title,
        description
    )