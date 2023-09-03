package com.mvi.sharednotes.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mvi.sharednotes.R

enum class Route(
    val titleResId: Int,
    val topBarVisibility: Boolean,
    private val menuItems: Array<MenuItems> = emptyArray()
) {

    LOGIN(R.string.title_login, false),

    HOME(R.string.title_home, true, arrayOf(MenuItems.LOGOUT)),

    CREATION(R.string.title_creation, true);

    @Composable
    fun toActions(
        onShare: () -> Unit = {},
        onLogOut: () -> Unit = {}
    ): @Composable RowScope.() -> Unit = {
        for (index in menuItems.indices) {
            when (menuItems[index]) {
                MenuItems.SHARE -> IconButton(onClick = onShare) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share", // TODO: add multi lang support
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                MenuItems.LOGOUT -> IconButton(onClick = onLogOut) {
                    Image(
                        painter = painterResource(R.drawable.logout),
                        // TODO: add multi lang support
                        contentDescription = "Logout",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

enum class MenuItems {
    SHARE,
    LOGOUT;
}