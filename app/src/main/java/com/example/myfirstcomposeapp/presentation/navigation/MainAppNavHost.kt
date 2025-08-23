package com.example.myfirstcomposeapp.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myfirstcomposeapp.presentation.screens.userDetail.PantallaDetalleUsuario
import com.example.myfirstcomposeapp.presentation.screens.users.PantallaUsuarios
import com.example.myfirstcomposeapp.data.dto.UserDTO
import com.example.myfirstcomposeapp.domain.models.UserModel
import com.example.myfirstcomposeapp.presentation.screens.login.LoginScreen
import com.google.gson.Gson

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    loginState: Boolean,
    userName: String
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = if(loginState) Login else Users(name = userName)
    ) {

        composable<Login> { LoginScreen(
            onEnterClick = { name ->
                navHostController.navigate(Users(name = name)) {
                    popUpTo(Login) { inclusive = true }
                }
            }
        ) }

        composable<Users> { backStackEntry ->
            val users: Users = backStackEntry.toRoute()
            PantallaUsuarios(name = users.name, onClickUsuario = { user ->
                val jsonString = Gson().toJson(user)
                val encodedJson = Uri.encode(jsonString)
                navHostController.navigate(DetailUser(userJson = encodedJson))

            })
        }

        composable<DetailUser> { backStackEntry ->
            val args = backStackEntry.toRoute<DetailUser>()
            val decodedJson = Uri.decode(args.userJson)
            val gson = Gson()
            val user = gson.fromJson(decodedJson, UserModel::class.java)
            PantallaDetalleUsuario(usuario = user)
        }
    }
}