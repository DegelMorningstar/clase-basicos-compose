package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonExample(modifier: Modifier = Modifier) {
    Button(
        onClick = { println("El boton fue presionado") }
    ) {
        Text("Presioname")
    }
}

@Preview
@Composable
private fun ButtonExamplePreview() {
    ButtonExample()
}