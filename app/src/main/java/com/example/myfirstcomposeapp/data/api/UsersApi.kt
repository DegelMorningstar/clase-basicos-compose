package com.example.myfirstcomposeapp.data.api

import com.example.myfirstcomposeapp.data.dto.UserListResponse
import retrofit2.http.GET

interface UsersApi {

    @GET("api/")
    suspend fun getUser(): UserListResponse

    @GET("ap")
    suspend fun getUsers(): UserListResponse

    @GET("api/?gender=female")
    suspend fun getFemaleUsers(): UserListResponse

    @GET("api/?nat=MX")
    suspend fun getMexicanUsers(): UserListResponse

}