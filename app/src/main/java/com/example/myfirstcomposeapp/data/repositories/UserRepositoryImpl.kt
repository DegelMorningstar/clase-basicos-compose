package com.example.myfirstcomposeapp.data.repositories

import com.example.myfirstcomposeapp.data.dto.User
import com.example.myfirstcomposeapp.data.api.UsersApi
import com.example.myfirstcomposeapp.domain.repositories.UserRepository

class UserRepositoryImpl(private val api: UsersApi) : UserRepository {

    override suspend fun obtenerTodosLosUsuarios():List<User> {
        return api.getUsers().results
    }

    override suspend fun obtenerUsuario(): User {
        return api.getUser().results.first()
    }

    override suspend fun obtenerUsuariosFemeninos():List<User> {
        return api.getFemaleUsers().results
    }

    override suspend fun obtenerUsuariosMexicanos():List<User> {
        return api.getMexicanUsers().results
    }

}