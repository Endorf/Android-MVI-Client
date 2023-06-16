package com.mvi.sharednotes.notes.view.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Note(
    val id: Int,
    val author: String,
    val tag: String,
    val title: String?,
    val description: String?,
    val icon: String? = null
) : Parcelable