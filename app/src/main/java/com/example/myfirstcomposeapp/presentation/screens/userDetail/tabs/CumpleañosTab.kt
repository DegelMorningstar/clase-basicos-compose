package com.example.myfirstcomposeapp.presentation.screens.userDetail.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.data.dto.User
import com.example.myfirstcomposeapp.util.formatToSimpleDate

@Composable
fun CumpleañosTab(usuario: User) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 32.dp))
        Text(
            text = "Mi cumpleaños es",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = usuario.dob.date.formatToSimpleDate(),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text("Tengo ${usuario.dob.age} años")
    }
}
