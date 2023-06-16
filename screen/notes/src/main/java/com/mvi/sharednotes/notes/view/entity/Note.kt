package com.mvi.sharednotes.notes.view.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Note(
    val id: Long,
    val userId: Long,
    val author:String,
    val tag:String,
    val icon:String,
    val title:String?,
    val description:String?
) : Parcelable