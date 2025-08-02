package com.example.myfirstcomposeapp.domain.repositories

import com.example.myfirstcomposeapp.data.dto.User

interface UserRepository {
    suspend fun obtenerTodosLosUsuarios():List<User>
    suspend fun obtenerUsuario(): User
    suspend fun obtenerUsuariosFemeninos():List<User>
    suspend fun obtenerUsuariosMexicanos():List<User>
}