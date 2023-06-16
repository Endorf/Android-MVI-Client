package com.mvi.sharednotes.notes.data.mappers

import com.mvi.sharednotes.network.data.api.user.entity.PostEntity
import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.storage.db.entity.NoteEntity

fun PostEntity.toNote(): Note =
    Note(
        id,
        "Unknown", // TODO: fill on Backend
        "noname",
        title,
        body
    )

fun List<PostEntity>.toNotes(): List<Note> = map { it.toNote() }

fun Note.toPostEntity(): PostEntity =
    PostEntity(
        id,
        title,
        description,
        username = author,
        tag = tag
    )

fun Note.toNoteEntity(): NoteEntity =
    NoteEntity(
        postId = id,
        title = title,
        body = description,
        username = author,
        tag = tag
    )

fun List<Note>.toNoteEntities() = map { it.toNoteEntity() }

fun NoteEntity.toNote(): Note =
    Note(
        postId,
        username,
        tag,
        title = title,
        description = body
    )

fun PostEntity.toNoteEntity(): NoteEntity =
    NoteEntity(
        postId = id,
        title = title,
        body = body,
        username = username,
        tag = tag
    )

fun NoteEntity.toPostEntity(): PostEntity =
    PostEntity(
        id = postId,
        title = title,
        body = body,
        username = username,
        tag = tag
    )

fun List<NoteEntity>.toPostEntities(): List<PostEntity> = map {
    it.toPostEntity()
}