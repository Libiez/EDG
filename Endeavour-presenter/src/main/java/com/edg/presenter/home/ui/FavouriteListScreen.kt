package com.edg.presenter.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.edg.presenter.home.HomeViewModel

@Composable
fun FavouriteListScreen(text: String) = Surface(modifier = Modifier.fillMaxSize()) {
    Text(text = text, fontSize = 32.sp)
}