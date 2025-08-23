package com.example.myfirstcomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl.Companion.PREF_INSTANCE
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl.Companion.PREF_LOGIN_STATE
import com.example.myfirstcomposeapp.data.repositories.PreferencesRepositoryImpl.Companion.PREF_NAME
import com.example.myfirstcomposeapp.domain.repositories.PreferencesRepository
import com.example.myfirstcomposeapp.presentation.navigation.MainAppNavHost
import com.example.myfirstcomposeapp.presentation.theme.AppTheme
import com.example.myfirstcomposeapp.util.AES
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var repository: PreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate called")
        val loginState : Boolean = repository.getLoginState()
        val userName : String = repository.getName()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                MainAppNavHost(
                    modifier = Modifier,
                    navHostController = navController,
                    loginState = loginState,
                    userName = userName
                )
            }
        }
    }
}