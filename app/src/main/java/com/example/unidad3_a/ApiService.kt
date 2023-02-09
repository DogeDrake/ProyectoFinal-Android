package com.example.unidad3_a

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("usuarios2")
    fun getUsers(
    ): Call<UserResponse>



}