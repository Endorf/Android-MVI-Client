package com.mvi.sharednotes.network.data.api.post.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemotePostEntity(
    val id: Int = -1,
    var title: String? = null,
    val body: String? = null,
    val username: String,
    val tag: String
) : Parcelable
