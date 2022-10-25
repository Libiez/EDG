package com.edg.presenter.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edg.presenter.R
import com.edg.presenter.home.HomeViewModel
import com.edg.presenter.home.ui.navigation.AppBottomNavigation
import com.edg.presenter.home.ui.navigation.NAV_FAV
import com.edg.presenter.home.ui.navigation.NAV_HOME



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) },
        drawerContent = { Text(text = "Drawer") }) {
        NavHost(navController = navController, startDestination = NAV_HOME) {
            composable(NAV_HOME) { ProductListScreen(viewModel) }
            composable(NAV_FAV) { FavouriteListScreen(viewModel) }
        }
    }
}

