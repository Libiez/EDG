package com.edg.presenter.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.edg.presenter.home.compose.HomeScreen
import com.edg.presenter.product.ProductActivity
import com.edg.presenter.ui.theme.EndeavourTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            EndeavourTheme {
                HomeScreen{
                   startActivity(ProductActivity.newIntent(this, it))
                }
            }
        }
    }
}

