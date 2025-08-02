package com.example.myfirstcomposeapp.presentation.screens.userDetail.tabs

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.data.dto.User

@Composable
fun DireccionTab(usuario: User, context: Context) {
    val location = usuario.location
    val direccion = "${location.street.name} ${location.street.number}, " +
            "${location.city}, ${location.state}, ${location.country}, CP ${location.postcode}"

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mi dirección es", color = Color.Gray)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(direccion, fontWeight = Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Button(onClick = {
            val latitude = location.coordinates.latitude
            val longitude = location.coordinates.longitude
            val uri = Uri.parse("geo:$latitude,$longitude?q=${Uri.encode(direccion)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }) {
            Text("Ver en mapa")
        }
    }
}
