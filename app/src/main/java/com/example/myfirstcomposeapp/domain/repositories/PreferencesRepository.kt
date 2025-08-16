package com.example.myfirstcomposeapp.domain.repositories

interface PreferencesRepository {
    fun saveName(name: String)
    fun updateLoginState(state: Boolean)
    fun getLoginState(): Boolean
    fun getName(): String
}