package com.mvi.sharednotes.notes.data.mappers

import com.mvi.sharednotes.network.data.api.post.entity.RemotePostEntity
import com.mvi.sharednotes.notes.view.entity.Note

// TODO: Fix on Backend
fun RemotePostEntity.toNote(): Note = Note(id, "Unknown", "noname", title, body)

fun List<RemotePostEntity>.toNoteList(): List<Note> = map { it.toNote() }
