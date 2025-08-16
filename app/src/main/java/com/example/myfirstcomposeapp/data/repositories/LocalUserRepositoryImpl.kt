package com.example.myfirstcomposeapp.data.repositories

import android.util.Log
import com.example.myfirstcomposeapp.data.db.UserDatabase
import com.example.myfirstcomposeapp.data.db.entities.UserEntity
import com.example.myfirstcomposeapp.data.dto.UserDTO
import com.example.myfirstcomposeapp.data.mapper.fromDTOtoEntity
import com.example.myfirstcomposeapp.domain.repositories.LocalUserRepository

class LocalUserRepositoryImpl(private val db:UserDatabase): LocalUserRepository {

    override suspend fun getAllUsers(): List<UserEntity> {
        Log.i("LocalUserRepository", "Fetching users from local database")
        return db.userDao().getAll()
    }

    override suspend fun saveUsers(userDTOS: List<UserDTO>) {
        Log.i("LocalUserRepository", "Saving users to local database")
        val listUsers:List<UserEntity> = userDTOS.map { user ->
            Log.i("LocalUserRepository", "Converting UserDTO to UserEntity")
            user.fromDTOtoEntity()
        }
        db.userDao().insertAll(listUsers)
    }

}