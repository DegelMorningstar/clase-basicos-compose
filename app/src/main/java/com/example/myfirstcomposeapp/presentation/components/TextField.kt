package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputExample(modifier: Modifier = Modifier) {

    var texto by remember { mutableStateOf("") }

    TextField(
        value = texto,
        onValueChange = { nuevoValor ->
            texto = nuevoValor
        }
    )

}

@Preview
@Composable
private fun InputExamplePreview() {
    InputExample()
}