package com.example.myfirstcomposeapp.presentation.screens.practica

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapp.data.dto.Usuario
import com.example.myfirstcomposeapp.presentation.components.CustomAlert
import com.example.myfirstcomposeapp.presentation.components.CyanList
import com.example.myfirstcomposeapp.presentation.components.UiState
import com.example.myfirstcomposeapp.presentation.theme.AppTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticaRoute(
    modifier: Modifier = Modifier,
    viewModel: PracticaViewModel
) {

    val estado by viewModel.uiState.collectAsState(initial = UiState.Loading)

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.getUsuarios()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Mi primera app en Compose")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Aqui van a ir botones, lo prometo")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Estimado usuario, su sesion ha expirado")
                }
            }) {
                Text("FAB")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        when(estado){
            is UiState.Error -> {
                val errorMessage = (estado as UiState.Error).message
                LaunchedEffect(errorMessage) {
                    scope.launch {
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }
            }
            UiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column {
                        CircularProgressIndicator()
                        Text("Cargando...")
                    }
                }
            }
            is UiState.Success<*> -> {
                PracticaScreen(
                    modifier = modifier.padding(innerPadding),
                    showAlert = viewModel.showAlert,
                    nombre = viewModel.nombre,
                    usuarios = viewModel.usuarios, //ESTOY LEYENDO LA LISTA DEL VIEWMODEL
                    onNombreChange = { nuevoValor ->
                        viewModel.onNameChange(nuevoValor)
                    },
                    onClickAddUser = { nombre ->
                        viewModel.addUsuario(nombre)
                    },
                    onShowAlet = {
                        viewModel.showAlert()
                    },
                    onHideAlert = {
                        viewModel.hideAlert()
                    },
                    onClickDeleteUser = { usuario ->
                        viewModel.deleteUsuario(usuario)
                    },
                    onClickDeleteAllUsers = {
                        viewModel.deleteAllUsers()
                    }
                )
            }
        }
    }
}

@Composable
fun PracticaScreen(
    modifier: Modifier = Modifier,
    showAlert: Boolean,
    nombre: String,
    usuarios: List<Usuario>, //SE RECIBE DESDE PracticaRoute
    onNombreChange: (String) -> Unit,
    onClickAddUser: (Usuario) -> Unit,
    onClickDeleteUser: (Usuario) -> Unit,
    onShowAlet: () -> Unit,
    onHideAlert: () -> Unit,
    onClickDeleteAllUsers: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            placeholder = { Text("Ingresa tu usuario") },
            value = nombre,
            onValueChange = { nuevoValor ->
                onNombreChange(nuevoValor)
            },
            modifier = Modifier
        )
        Spacer(Modifier.padding(top = 16.dp))
        Button(onClick = {
            onClickAddUser(Usuario(nombre, 25)) //AQUI SE CREA UN USUARIO CON EDAD FIJA
            if(usuarios.count() > 5) {
                onShowAlet()
            }
            Log.i("Practica", "se agrego al usuario $nombre")
        }) {
            Text("Agregar usuario")
        }
        Spacer(Modifier.padding(top = 16.dp))
        Button(onClick = { onClickDeleteAllUsers()}) {
            Text("Eliminar todos los usuarios")
        }
        //MI LISTA DE USUARIOS SIRVE PARA PINTAR MIS CARDS
        CyanList(usuarios){ usuario ->
            onClickDeleteUser(usuario)
            Log.i("Practica", "se elimino al usuario $usuario")
        }
    }
    CustomAlert(showAlert) {
        onHideAlert()
    }
}




@Preview(showBackground = true)
@Composable
private fun PracticaPreview() {
    AppTheme {
        PracticaScreen(
            showAlert = false,
            nombre = "",
            usuarios = listOf(Usuario("Juan", 25), Usuario("Ana", 30), Usuario("Luis", 28)),
            onNombreChange = {},
            onClickAddUser = {},
            onShowAlet = {},
            onHideAlert = {},
            onClickDeleteUser = {},
            onClickDeleteAllUsers = {}
        )
    }
}