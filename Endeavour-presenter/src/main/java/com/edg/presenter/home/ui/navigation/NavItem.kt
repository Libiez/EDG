package com.edg.presenter.home.ui.navigation


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

import com.edg.presenter.R

sealed class NavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val navRoute: String
) {
    object Home : NavItem(R.string.home, R.drawable.ic_baseline_home_24, NAV_HOME)
    object Fav : NavItem(R.string.fav, R.drawable.ic_baseline_favorite_24, NAV_FAV)
}
