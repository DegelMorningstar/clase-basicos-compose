package com.example.myfirstcomposeapp.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstcomposeapp.data.api.ApiFactory
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl
import com.example.myfirstcomposeapp.data.repositories.UserRepositoryImpl
import com.example.myfirstcomposeapp.domain.repositories.PreferencesRepository
import com.example.myfirstcomposeapp.domain.useCase.GetAllUsersUseCase
import com.example.myfirstcomposeapp.presentation.screens.users.UserViewModel
import com.example.myfirstcomposeapp.util.onlyLettersAndSpaces

class LoginViewModelFactory(private val repository: PreferencesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class LoginViewModel(private val repository: PreferencesRepository) : ViewModel() {

    var name by mutableStateOf("")
        private set
    var nameError by mutableStateOf(false)
        private set

    fun onNameChange(newName: String) {
        if(newName.onlyLettersAndSpaces()){
            name = newName
            nameError = false
        }
    }
    fun updateNameError(error: Boolean) {
        nameError = error
    }
    fun saveUser(name: String){
        repository.saveName(name)
        repository.updateLoginState(false)
    }

}