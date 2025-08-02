package com.example.myfirstcomposeapp.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
object Login

@Serializable
data class Users(val name: String)

@Serializable
data class DetailUser(val userJson: String)