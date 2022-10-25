package com.edg.presenter.home.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.edg.domain.models.products.Product
import com.edg.presenter.home.HomeViewModel
import com.edg.presenter.ui.theme.OFF_WHITE
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductListScreen(
    viewModel: HomeViewModel,
    navigateToProductDetails: (Product) -> Unit) = Surface(modifier = Modifier.fillMaxSize()) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.UIEvent.ShowSnack -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    ProductListing(state.productsItems,viewModel,navigateToProductDetails)
}

@Composable
fun ProductListing(productsItems: List<Product>, viewModel: HomeViewModel,navigateToProductDetails: (Product) -> Unit) {

    Box(
        modifier = Modifier.background(OFF_WHITE)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp, bottom = 4.dp)
                .padding(bottom = 56.dp)
        ) {

            items(productsItems.size) { index ->
                val product = productsItems[index]
                if (index > 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                ProductItem(item = product,viewModel,navigateToProductDetails)
            }

        }

    }
}

@Composable
fun ProductItem(item: Product, viewModel: HomeViewModel,navigateToProductDetails: (Product) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .clickable {navigateToProductDetails(item) },
        elevation = 2.dp
    ) {
        Column() {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                ImageLoader(item.imageURL)
                Spacer(modifier = Modifier.width(8.dp))
                ContentLoader(item)
            }
            BottomLayout(item,viewModel)

        }
    }
}

@Composable
fun BottomLayout(product: Product, viewModel: HomeViewModel) {
    Spacer(modifier = Modifier.height(0.1.dp))
    Divider(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        color = Color.Gray,
        thickness = 1.dp
    )
    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Spacer(modifier = Modifier.width(1.dp))
        Spacer(Modifier.weight(1f))


        IconButton(
            modifier = Modifier
                .size(24.dp),
            onClick = {
                viewModel.addFavouriteProduct(product)
            }
        ) {
            Icon(
                Icons.Rounded.FavoriteBorder,
                "contentDescription",

                )
        }

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(
            modifier = Modifier
                .size(24.dp),
            onClick = {

            }
        ) {
            Icon(
                Icons.Outlined.ShoppingBag,
                "contentDescription",

                )
        }

        Spacer(modifier = Modifier.width(20.dp))
    }
}

@Composable
fun ImageLoader(imageUrl: String) {

    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(80.dp)
            .rotate(30.0F)
    )
}

@Composable
fun ContentLoader(item: Product) {

    Column {
        Text(
            text = item.title,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.brand,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h6
            )

         Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "$ ${item.price?.get(0)?.value.toString()}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h6
        )



    }
}
