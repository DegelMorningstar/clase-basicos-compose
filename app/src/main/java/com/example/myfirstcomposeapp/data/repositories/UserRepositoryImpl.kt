package com.example.myfirstcomposeapp.data.repositories

import android.util.Log
import com.example.myfirstcomposeapp.data.dto.UserDTO
import com.example.myfirstcomposeapp.data.api.UsersApi
import com.example.myfirstcomposeapp.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UsersApi
) : UserRepository {

    override suspend fun obtenerTodosLosUsuarios():List<UserDTO> {
        Log.i("UserRepositoryImpl", "Fetching all users from remote API")
        return api.getUsers().results
    }

    override suspend fun obtenerUsuario(): UserDTO {
        return api.getUser().results.first()
    }

    override suspend fun obtenerUsuariosFemeninos():List<UserDTO> {
        return api.getFemaleUsers().results
    }

    override suspend fun obtenerUsuariosMexicanos():List<UserDTO> {
        return api.getMexicanUsers().results
    }

}