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
import com.example.myfirstcomposeapp.presentation.navigation.MainAppNavHost
import com.example.myfirstcomposeapp.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate called")
        val preferences = getSharedPreferences(PREF_INSTANCE, MODE_PRIVATE)
        val repository = PreferencesRepositoryImpl(preferences)
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