package com.mvi.sharednotes.notes.data.mappers

import com.mvi.sharednotes.notes.view.entity.Note
import com.mvi.sharednotes.storage.entities.LocalPostEntity

fun Note.toLocalPostEntity() = LocalPostEntity(id, author, tag, title, description)

fun LocalPostEntity.toNote() = Note(postId, username, tag, title, description)

fun List<LocalPostEntity>.toNoteList() = map { it.toNote() }

fun List<Note>.toLocalPostEntityList() = map { it.toLocalPostEntity() }

