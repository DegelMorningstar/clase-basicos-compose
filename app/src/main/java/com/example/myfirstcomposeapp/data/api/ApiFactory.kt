package com.example.myfirstcomposeapp.data.api

import com.example.myfirstcomposeapp.data.api.UsersApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://randomuser.me/"

    fun getApiService(): UsersApi {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UsersApi::class.java)
    }

}