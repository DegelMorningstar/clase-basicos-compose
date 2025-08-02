package com.example.myfirstcomposeapp.data.repositories

import androidx.compose.runtime.mutableStateListOf
import com.example.myfirstcomposeapp.data.dto.Usuario

class PracticaRepository {

    private val usuarios = mutableStateListOf<Usuario>()

    fun addUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun deleteUsuario(usuario: Usuario) {
        usuarios.remove(usuario)
    }

    fun getUsuarios() = usuarios

    fun clearUsuarios() {
        usuarios.clear()
    }

}