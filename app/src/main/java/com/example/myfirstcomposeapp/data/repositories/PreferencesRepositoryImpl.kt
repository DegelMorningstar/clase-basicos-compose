package com.example.myfirstcomposeapp.data.repositories

import android.content.SharedPreferences
import com.example.myfirstcomposeapp.domain.repositories.PreferencesRepository
import androidx.core.content.edit
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): PreferencesRepository {

    companion object {
        const val PREF_INSTANCE = "myfirstapp_preferences"
        const val PREF_LOGIN_STATE = "login_state"
        const val PREF_NAME = "name"
    }

    override fun saveName(name: String) {
        sharedPreferences.edit {
            putString(PREF_NAME, name)
        }
    }

    override fun updateLoginState(state: Boolean) {
        sharedPreferences.edit {
            putBoolean(PREF_LOGIN_STATE,state)
        }
    }

    override fun getLoginState(): Boolean {
        return sharedPreferences.getBoolean(PREF_LOGIN_STATE, true)
    }

    override fun getName(): String {
        return sharedPreferences.getString(PREF_NAME, "") ?: ""
    }
}