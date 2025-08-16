package com.example.myfirstcomposeapp.domain.repositories

import com.example.myfirstcomposeapp.data.db.entities.UserEntity
import com.example.myfirstcomposeapp.data.dto.UserDTO

interface LocalUserRepository {
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun saveUsers(userDTOS: List<UserDTO>)
}