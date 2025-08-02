package com.example.myfirstcomposeapp.domain.useCase

import com.example.myfirstcomposeapp.domain.repositories.UserRepository

class GetAllUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.obtenerTodosLosUsuarios()
}