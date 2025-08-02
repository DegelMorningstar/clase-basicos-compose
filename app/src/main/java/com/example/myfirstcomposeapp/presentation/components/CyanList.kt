package com.example.myfirstcomposeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.R
import com.example.myfirstcomposeapp.data.dto.Usuario

@Composable
fun CyanList(
    usuarios: List<Usuario>, //SE RECIBE DESDE PRACTICA SCREEN
    onDeleteClick: (Usuario) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.Cyan)
    ) {
        //SE PINTA UNA ROW POR CADA ELEMENTO EN MI LISTA
        items(usuarios) { usuario ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //EL USUARIO SE ENVIA AL TEXT PARA PINTAR EL VALOR
                Column {
                    Text(text = usuario.nombre, modifier = Modifier.padding(8.dp))
                    Text(text = "${usuario.edad}", modifier = Modifier)
                }
                //EL BOTON ON CLICK DESENCADENA UN EVENTO ENVIANDO UN USUARIO HACIA PRACTICA SCREEN
                IconButton(onClick = { onDeleteClick(usuario) }) {
                    Icon(
                        painter = painterResource(R.drawable.trash),
                        contentDescription = null
                    )
                }
            }
        }
    }
}