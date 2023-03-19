package com.mvi.sharednotes.login.view.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserCredentials(
    val email: String
) : Parcelable