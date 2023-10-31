package com.example.androidimagefilters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.androidimagefilters.ui.theme.AndroidImageFiltersTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidImageFiltersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val imageURL = "https://picsum.photos/id/11/200/300"
    val imageSize = 500.dp

    val imageRequest = ImageRequest.Builder(context)
        .data(imageURL)
        .build()

    val imageCircleCropRequest = ImageRequest.Builder(context)
        .data(imageURL)
        .transformations(CircleCropTransformation())
        .build()

    val imageRoundedCornersRequest = ImageRequest.Builder(context)
        .data(imageURL)
        .transformations(RoundedCornersTransformation(radius = 10f))
        .build()


    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(12.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            modifier = modifier.padding(12.dp),
            text = "Image loaded from the internet using coil API"
        )
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier.size(size = imageSize)
        )

        Divider(Modifier.padding(vertical = 30.dp))

        Text(
            modifier = modifier.padding(12.dp),
            text = "Image with blur effect"
        )

        BlurredImage(imageRequest = imageRequest, imageSize = imageSize)

        Divider(Modifier.padding(vertical = 30.dp))

        Text(
            modifier = modifier.padding(12.dp),
            text = "Image cropped to a circle"
        )

        AsyncImage(
            model = imageCircleCropRequest,
            contentDescription = null,
            modifier = Modifier
                .size(size = imageSize)

        )

        Divider(Modifier.padding(vertical = 30.dp))

        Text(
            modifier = modifier.padding(12.dp),
            text = "Image with rounded corners"
        )

        AsyncImage(
            model = imageRoundedCornersRequest,
            contentDescription = null,
            modifier = Modifier
                .size(size = imageSize)

        )

    }
}

@Composable
fun BlurredImage(imageRequest: ImageRequest, imageSize: Dp) {
    var radius by remember { mutableStateOf(10.dp) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier
                .size(size = imageSize)
                .blur(radius = radius, BlurredEdgeTreatment.Rectangle)
        )

        Slider(
            value = radius.value,
            valueRange = 1f..10f,
            onValueChange = { radius = it.dp },
            steps = 9,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(text = "Blur effect: ${radius.value.toInt()}")
    }

}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    AndroidImageFiltersTheme {
        AppScreen()
    }
}