package com.mvi.sharednotes.network.data.api.user.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteUserEntity(
    val id: Int = -1,
    var email: String,
    val name: String? = null,
    @SerializedName("username") val userName: String? = null
) : Parcelable