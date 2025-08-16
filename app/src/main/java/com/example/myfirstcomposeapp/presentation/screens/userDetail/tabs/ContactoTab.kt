package com.example.myfirstcomposeapp.presentation.screens.userDetail.tabs

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.data.dto.UserDTO
import androidx.core.net.toUri
import com.example.myfirstcomposeapp.domain.models.UserModel

@Composable
fun ContactoTab(usuario: UserModel, context: Context) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize().padding(top = 32.dp, start = 16.dp)
    ) {
        TextButton(onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:${usuario.email}".toUri()
            }
            context.startActivity(intent)
        }) {
            Icon(Icons.Filled.Email, null)
            Text("Correo electronico: ${usuario.email}")
        }

        TextButton(onClick = {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:${usuario.phone}".toUri()
            }
            context.startActivity(intent)
        }) {
            Icon(Icons.Filled.Phone, null)
            Text("Telefono de casao: ${usuario.phone}")
        }

        TextButton(onClick = {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:${usuario.cellPhone}".toUri()
            }
            context.startActivity(intent)
        }) {
            Icon(Icons.Filled.Phone, null)
            Text("Telefono celular: ${usuario.cellPhone}")
        }
    }
}
