package com.example.myfirstcomposeapp.presentation.screens.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myfirstcomposeapp.presentation.components.UiState
import com.example.myfirstcomposeapp.data.api.ApiFactory
import com.example.myfirstcomposeapp.data.dto.User
import com.example.myfirstcomposeapp.data.repositories.UserRepositoryImpl
import com.example.myfirstcomposeapp.domain.useCase.GetAllUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object UserContainer {
    private val api = ApiFactory.getApiService()
    val repository = UserRepositoryImpl(api)
    val useCase = GetAllUsersUseCase(repository)
}

class UserViewModelFactory(private val useCase: GetAllUsersUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class UserViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun consultarUsuarios(){
        Log.i("UserViewModel", "consultarUsuarios called")
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val usuarios = getAllUsersUseCase()
                _uiState.value = UiState.Success<List<User>>(usuarios)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar los usuarios: ${e.message}")
            }
        }
    }

}