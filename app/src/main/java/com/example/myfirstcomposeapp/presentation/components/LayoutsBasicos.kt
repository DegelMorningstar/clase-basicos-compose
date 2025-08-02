package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_2
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EjemploLayout(modifier: Modifier = Modifier) {
    //Alinea los elementos de manera vertical
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
        Text("Elemento 1")
        Text("Elemento 2")
        Text("Elemento 3")
        //Alinea los elementos de manera horizontal
        Row {
            Text("Fila 1")
            Text("Fila 2")
            Text("Fila 3")
            Text("Fila 4")
        }
        Text("Elemento 4")
        //Alinea los elementos dentro de una caja
        Box {
            Text("Estoy dentro de una caja")
        }
    }
}

@Preview(showBackground = true, device = PIXEL_2)
@Composable
private fun EjemploLayoutPreview() {
    EjemploLayout()
}