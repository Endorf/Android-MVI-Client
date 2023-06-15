package com.mvi.sharednotes.notes.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Notes(
    val id: Long,
    val author:String,
    val tag:String,
    val icon:String,
    val title:String,
    val description:String
) : Parcelable