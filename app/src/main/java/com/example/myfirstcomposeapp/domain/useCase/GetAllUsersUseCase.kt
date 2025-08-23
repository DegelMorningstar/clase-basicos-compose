package com.example.myfirstcomposeapp.domain.useCase

import android.util.Log
import com.example.myfirstcomposeapp.data.db.entities.UserEntity
import com.example.myfirstcomposeapp.data.dto.UserDTO
import com.example.myfirstcomposeapp.data.mapper.fromDTOtoDomain
import com.example.myfirstcomposeapp.data.mapper.fromEntitytoDomain
import com.example.myfirstcomposeapp.domain.models.UserModel
import com.example.myfirstcomposeapp.domain.repositories.LocalUserRepository
import com.example.myfirstcomposeapp.domain.repositories.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val remoteUserRepository: UserRepository,
    private val localUserRepository: LocalUserRepository
) {

    suspend operator fun invoke(update: Boolean): List<UserModel> {
        val localUsers: List<UserEntity> = localUserRepository.getAllUsers()
        if(localUsers.isEmpty() || update){
            Log.i("GetAllUsersUseCase", "No local users found, fetching from remote")
            val remoteUsers: List<UserDTO> = remoteUserRepository.obtenerTodosLosUsuarios()
            localUserRepository.saveUsers(remoteUsers)
            return remoteUsers.map {
                Log.i("GetAllUsersUseCase", "Converting UserDTO to UserModel")
                it.fromDTOtoDomain()
            }
        }else{
            Log.i("GetAllUsersUseCase", "Returning users from local database")
            return localUsers.map {
                Log.i("GetAllUsersUseCase", "Converting UserEntity to UserModel")
                it.fromEntitytoDomain()
            }
        }
    }

}