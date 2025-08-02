package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfirstcomposeapp.R

@Composable
fun ImageExample(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Logo"
    )
}

@Preview
@Composable
private fun ImageExamplePreview() {
    ImageExample()
}