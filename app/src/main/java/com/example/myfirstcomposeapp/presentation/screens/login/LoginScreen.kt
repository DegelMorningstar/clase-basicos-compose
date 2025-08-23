package com.example.myfirstcomposeapp.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myfirstcomposeapp.R
import com.example.myfirstcomposeapp.presentation.theme.backgroundColor
import com.example.myfirstcomposeapp.presentation.theme.primaryOrange

@Composable
fun LoginScreen(
    onEnterClick: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryOrange),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxSize(0.8f),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_image),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Text(
                    text = "Bienvenido",
                    color = primaryOrange,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = {
                        viewModel.onNameChange(it)
                    },
                    placeholder = { Text(text = "Por favor, ingrese su nombre") },
                    label = { Text(text = "Nombre") },
                    isError = viewModel.nameError,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                if(viewModel.nameError){
                    Text(
                        text = "Debe ingresar un nombre para continuar",
                        fontSize = 12.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Button(onClick = {
                    if(viewModel.name.isNotEmpty()){
                        onEnterClick(viewModel.name)
                        viewModel.saveUser(viewModel.name)
                    }else{
                        viewModel.updateNameError(true)
                    }
                }) {
                    Text(text = "Enter")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun LoginPreview() {
//    AppTheme {
//        LoginScreen {  }
//    }
//}