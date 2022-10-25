package com.edg.presenter.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.edg.domain.models.products.Product
import com.edg.presenter.product.compose.ProductScreen
import com.edg.presenter.ui.theme.EndeavourTheme


class ProductActivity : ComponentActivity() {

    private val product: Product by lazy {
    intent?.getParcelableExtra(PRODUCT)!!

}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EndeavourTheme {
                ProductScreen(product)
            }
        }
    }

    companion object {
        private const val PRODUCT = "product"
        fun newIntent(context: Context, product: Product) =
            Intent(context, ProductActivity::class.java).apply {
                putExtra(PRODUCT, product)
            }
    }
}