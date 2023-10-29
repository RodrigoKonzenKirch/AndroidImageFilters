package com.example.androidimagefilters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.androidimagefilters.ui.theme.AndroidImageFiltersTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidImageFiltersTheme {
                // A surface container using the 'background' color from the theme
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

   FilteredImage()
}

@Composable
fun FilteredImage() {

    AsyncImage(
        model = "https://picsum.photos/id/11/200/300",
        contentDescription = null,
        modifier = Modifier.size(100.dp)
    )

}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    AndroidImageFiltersTheme {
        AppScreen()
    }
}