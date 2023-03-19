package com.mvi.sharednotes.network.data.api.user.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val id: Int = -1,
    var email: String? = null,
    val name: String? = null,
    val userName: String? = null
) : Parcelable