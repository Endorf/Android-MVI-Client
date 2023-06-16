package com.mvi.sharednotes.notes.data.mappers

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.storage.db.entity.NoteEntity

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

fun Note.toNotes(): NoteEntity =
    NoteEntity(
        postId = id,
        userId = userId,
        title = title,
        body = description
    )

fun NoteEntity.toNotes(): Note =
    Note(
        postId,
        userId,
        "",
        "",
        "",
        title,
        body
    )