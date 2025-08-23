package com.example.myfirstcomposeapp.presentation.screens.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myfirstcomposeapp.presentation.components.UiState
import com.example.myfirstcomposeapp.data.db.UserDatabase
import com.example.myfirstcomposeapp.data.dto.UserDTO
import com.example.myfirstcomposeapp.data.repositories.LocalUserRepositoryImpl
import com.example.myfirstcomposeapp.data.repositories.UserRepositoryImpl
import com.example.myfirstcomposeapp.domain.models.UserModel
import com.example.myfirstcomposeapp.domain.useCase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun consultarUsuarios(update: Boolean){
        Log.i("UserViewModel", "consultarUsuarios called")
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val usuarios = withContext(Dispatchers.IO) {
                    getAllUsersUseCase(update)
                }
                _uiState.value = UiState.Success<List<UserModel>>(usuarios)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar los usuarios: ${e.message}")
            }
        }
    }

}