package com.example.myfirstcomposeapp.presentation.screens.practica

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstcomposeapp.data.dto.Usuario
import com.example.myfirstcomposeapp.data.repositories.PracticaRepository
import com.example.myfirstcomposeapp.presentation.components.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PracticaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    val repository = PracticaRepository()

    var showAlert by mutableStateOf(false)
        private set

    var nombre by mutableStateOf("")
        private set

    var usuarios = mutableStateListOf<Usuario>()
        private set

    init {
        Log.i("PracticaViewModel", "ViewModel initialized")
        viewModelScope.launch {
            delay(5000)
            _uiState.value = UiState.Success("Data loaded successfully")
            delay(5000)
            _uiState.value = UiState.Error("Error loading data")
        }
    }

    fun showAlert() {
        showAlert = true
        Log.i("PracticaViewModel", "Alert shown")
    }
    fun hideAlert() {
        showAlert = false
        Log.i("PracticaViewModel", "Alert hidden")
    }
    fun onNameChange(newNombre: String) {
        nombre = newNombre
        Log.i("PracticaViewModel", "Nombre set to: $nombre")
    }

    fun getUsuarios() {
        viewModelScope.launch {
            Log.i("PracticaViewModel", "Fetching usuarios")
            delay(5000)
            val usuariosResponse = repository.getUsuarios()
            Log.i("PracticaViewModel", "Usuarios fetched: $usuariosResponse")
            usuarios = usuariosResponse
        }
    }
    fun addUsuario(usuario: Usuario) {
        //usuarios.add(usuario)
        Log.i("PracticaViewModel", "Usuario added: $usuario")
        repository.addUsuario(usuario)
        getUsuarios()
    }
    fun deleteUsuario(usuario: Usuario) {
        //usuarios.remove(usuario)
        Log.i("PracticaViewModel", "Usuario deleted: $usuario")
        repository.deleteUsuario(usuario)
        getUsuarios()
    }
    fun deleteAllUsers(){
        repository.clearUsuarios()
        getUsuarios()
    }

    override fun onCleared() {
        Log.i("PracticaViewModel", "ViewModel cleared")
        super.onCleared()
    }

}