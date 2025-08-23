package com.example.myfirstcomposeapp.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myfirstcomposeapp.domain.repositories.PreferencesRepository
import com.example.myfirstcomposeapp.util.onlyLettersAndSpaces
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: PreferencesRepository
) : ViewModel() {

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