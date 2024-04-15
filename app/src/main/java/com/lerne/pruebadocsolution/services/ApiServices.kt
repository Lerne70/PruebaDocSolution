package com.lerne.pruebadocsolution.services

import com.lerne.pruebadocsolution.model.Login
import com.lerne.pruebadocsolution.model.NewUser
import com.lerne.pruebadocsolution.model.NewUserResponse
import com.lerne.pruebadocsolution.model.loginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServices {

    @POST("authentication/authentication")
    suspend fun login(@Body login: Login): Response<loginResponse>

    @POST("user/RegisterUserRole")
    suspend fun newUser(
        @Body newUser: NewUser,
        @Header("Authorization") token: String): Response<NewUserResponse>

}