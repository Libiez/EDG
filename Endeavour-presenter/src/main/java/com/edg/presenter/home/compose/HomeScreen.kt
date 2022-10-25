package com.edg.presenter.home.compose

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edg.domain.models.products.Product
import com.edg.presenter.home.HomeViewModel
import com.edg.presenter.home.compose.navigation.AppBottomNavigation
import com.edg.presenter.home.compose.navigation.NAV_FAV
import com.edg.presenter.home.compose.navigation.NAV_HOME



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navigateToProductDetails: (Product) -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) },
        drawerContent = null) {
        NavHost(navController = navController, startDestination = NAV_HOME) {
            composable(NAV_HOME) { ProductListScreen(viewModel,navigateToProductDetails) }
            composable(NAV_FAV) { FavouriteListScreen(viewModel) }
        }
    }
}

