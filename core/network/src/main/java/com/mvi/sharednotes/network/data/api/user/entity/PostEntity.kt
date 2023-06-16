package com.mvi.sharednotes.network.data.api.user.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostEntity(
    val id: Int = -1,
    var title: String? = null,
    val body: String? = null,
    val username: String,
    val tag: String
) : Parcelable
