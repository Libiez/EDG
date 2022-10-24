package com.edg.presenter.home.ui.navigation


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

import com.edg.presenter.R

sealed class NavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: ImageVector,
    val navRoute: String
) {
    object Home : NavItem(R.string.home, Icons.Default.Home, NAV_HOME)
    object Fav : NavItem(R.string.fav,Icons.Default.Favorite, NAV_FAV)
}
