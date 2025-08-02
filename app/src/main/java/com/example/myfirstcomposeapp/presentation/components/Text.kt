package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Saludo(text: String){
    Text(text)
}

@Preview(showBackground = true)
@Composable
fun SaludoPreview(){
    Column {
        Saludo("Hola mundo desde jetpack compose")
        Saludo("Hola")
        Saludo("Adios")
        Saludo("MUCHO GUSTOOOOOO")
    }
}

