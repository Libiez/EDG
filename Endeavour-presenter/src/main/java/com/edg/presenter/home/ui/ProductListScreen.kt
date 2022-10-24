package com.edg.presenter.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.edg.domain.models.products.Product
import com.edg.presenter.R
import com.edg.presenter.home.HomeViewModel
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductListScreen(viewModel: HomeViewModel) = Surface(modifier = Modifier.fillMaxSize()) {

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

    ProductListing(state.productsItems)
}

@Composable
fun ProductListing(productsItems: List<Product>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {

        items(productsItems.size) { index ->
            val product = productsItems[index]
            if (index > 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            ProductItem(item = product)
        }

    }

}

@Composable
fun ProductItem(item: Product) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            ImageLoader(item.imageURL)
            Spacer(modifier = Modifier.width(8.dp))
            ContentLoader(item)
        }

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
            //.border(1.dp, Color.Gray, RoundedCornerShape(1))


    )
}

@Composable
fun ContentLoader(item: Product) {

    val robotoslab = FontFamily(
        Font(R.font.robotoslab_light, FontWeight.Light),
        Font(R.font.robotoslab_regular, FontWeight.Normal),
        Font(R.font.robotoslab_light, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.robotoslab_medium, FontWeight.Medium),
        Font(R.font.robotoslab_bold, FontWeight.Bold)
    )

    Column {
        Text(
            text = item.title,
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily =robotoslab,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(4.dp))

        Surface(shape = MaterialTheme.shapes.medium) {
            Text(
                text = item.brand,
                fontSize = 15.sp,
                fontFamily =robotoslab,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.h6
            )
        }
    }
}
