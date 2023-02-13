package com.example.unidad3_a

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("usuarios2")
    fun getUsers(
    ): Call<UserResponse>


    @GET("rutinas2")
    fun getUserRutines(
    ): Call<UserRutinasResponse>

    @GET("rutinas2?populate=*")
    fun getUserRutinesPopualte(
    ): Call<RutinaPopulateResponse>

    @GET("ejercicios2")
    fun getEjercicios(
    ): Call<EjerciciosResponse>

    @POST("rutinas2")
    fun postRutinas(
    ): Call<RutinaPopulateResponse>


}



