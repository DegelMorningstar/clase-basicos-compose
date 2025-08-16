package com.example.myfirstcomposeapp.domain.repositories

import com.example.myfirstcomposeapp.data.dto.UserDTO

interface UserRepository {
    suspend fun obtenerTodosLosUsuarios():List<UserDTO>
    suspend fun obtenerUsuario(): UserDTO
    suspend fun obtenerUsuariosFemeninos():List<UserDTO>
    suspend fun obtenerUsuariosMexicanos():List<UserDTO>
}