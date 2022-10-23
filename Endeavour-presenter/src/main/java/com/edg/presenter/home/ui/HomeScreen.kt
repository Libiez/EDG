package com.edg.presenter.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edg.presenter.home.HomeViewModel
import com.edg.presenter.home.ui.navigation.AppBottomNavigation
import com.edg.presenter.home.ui.navigation.NAV_FAV
import com.edg.presenter.home.ui.navigation.NAV_HOME



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) },
        drawerContent = { Text(text = "Drawer") }) {
        NavHost(navController = navController, startDestination = NAV_HOME) {
            composable(NAV_HOME) { ProductListScreen(text = "Home screen") }
            composable(NAV_FAV) { FavouriteListScreen(text = "Feed screen") }
        }
    }
}

@Composable
fun AppScreen(text: String) = Surface(modifier = Modifier.fillMaxSize()) {
    Text(text = text, fontSize = 32.sp)
}

