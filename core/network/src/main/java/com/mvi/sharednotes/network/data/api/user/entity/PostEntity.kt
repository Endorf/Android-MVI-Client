package com.mvi.sharednotes.network.data.api.user.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostEntity(
    val id: Long = -1,
    val userId: Long = -1,
    var title: String? = null,
    val body: String? = null
) : Parcelable
