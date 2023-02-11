package com.example.unidad3_a

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("usuarios2")
    fun getUsers(
    ): Call<UserResponse>


    @GET("rutinas2")
    fun getUserRutines(
    ): Call<UserRutinasResponse>



}