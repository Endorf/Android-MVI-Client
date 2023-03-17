package com.mvi.sharednotes.view.ui

import com.mvi.sharednotes.R

enum class Route(
    val titleResId: Int,
    val topBarVisibility: Boolean
) {

    LOGIN(R.string.title_login, false),

    HOME(R.string.title_home, true),
}