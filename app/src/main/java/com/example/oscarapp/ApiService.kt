package com.example.oscarapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class LoginRequest(val login: String, val senha: String)
data class LoginResponse(val success: Boolean, val message: String)

interface ApiService {
    @POST("/auth/login")
    fun autenticar(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/users")
    fun listarUsuarios(): Call<List<Usuario>>
}
