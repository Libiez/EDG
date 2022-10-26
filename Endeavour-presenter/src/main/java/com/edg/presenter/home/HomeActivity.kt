package com.edg.presenter.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.edg.presenter.home.compose.HomeScreen
import com.edg.presenter.product.ProductDetailsActivity
import com.edg.presenter.ui.theme.EndeavourTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            EndeavourTheme {
                HomeScreen{
                   startActivity(ProductDetailsActivity.newIntent(this, it))
                }
            }


        }
    }
}

