package com.edg.presenter.product.compose

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.edg.domain.models.products.Product
import com.edg.presenter.R
import com.edg.presenter.product.ProductDetailsViewModel
import com.edg.presenter.utils.AnimatingFabContent


@Composable
fun ProductScreen(product: Product) {
    val scrollState = rememberScrollState()
    val viewModel = hiltViewModel<ProductDetailsViewModel>()

    viewModel.isProductExist(product.id)

    ToolBar(viewModel, product)

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ToolBar(viewModel, product)
                    ProfileHeader(
                        scrollState,
                        product,
                        this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(product, this@BoxWithConstraints.maxHeight)
                }
            }
            AdoptFab(
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun ToolBar(viewModel: ProductDetailsViewModel, product: Product) {
    val activity = (LocalContext.current as? Activity)

    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("")
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = { activity?.finish() }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = {
                    if (viewModel.stateVariable) viewModel.removeFavouriteProduct(product.id) else viewModel.addFavouriteProduct(
                        product
                    )
                }) {
                    Icon(
                        imageVector = if (viewModel.stateVariable) Icons.Filled.Favorite else Icons.TwoTone.Favorite,
                        null
                    )
                }
            })
    }
}


@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    product: Product,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = rememberAsyncImagePainter(model = product.imageURL),
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}

@Composable
private fun ProfileContent(product: Product, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(product.title)

        ProfileProperty(stringResource(R.string.id), " ${product.id}")

        ProfileProperty(stringResource(R.string.brand), " ${product.brand}")

        ProfileProperty(stringResource(R.string.price), " ${product.price[0].value}")

        ProfileProperty(stringResource(R.string.rating), " ${product.ratingCount}")

        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(productName: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            productName = productName,
            modifier = Modifier
        )
    }
}

@Composable
private fun Name(productName: String, modifier: Modifier = Modifier) {
    Text(
        text = productName,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier,
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier,
            style = style
        )
    }
}

@Composable
fun AdoptFab(extended: Boolean, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { /* TODO */ },
        modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {

        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.AddShoppingCart,
                    contentDescription = stringResource(R.string.add_to_cart)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.add_to_cart),
                )
            },
            extended = extended

        )

    }
}
