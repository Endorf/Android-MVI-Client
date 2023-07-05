package com.mvi.sharednotes.storage.entities.mapper

import com.mvi.sharednotes.storage.db.entity.DbNoteEntity
import com.mvi.sharednotes.storage.entities.LocalPostEntity

fun LocalPostEntity.toDbPostEntity() = DbNoteEntity(postId, username, tag, title, description)

fun DbNoteEntity.toLocalPostEntity() = LocalPostEntity(postId, username, tag, title, body)

fun List<DbNoteEntity>.toLocalPostEntityList() = map {
    LocalPostEntity(it.postId, it.username, it.tag, it.title, it.body)
}

fun List<LocalPostEntity>.toDbPostEntityList() = map {
    DbNoteEntity(it.postId, it.username, it.tag, it.title, it.description)
}