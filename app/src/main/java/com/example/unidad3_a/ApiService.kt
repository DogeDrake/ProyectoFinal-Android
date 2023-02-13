package com.example.unidad3_a

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    //potater
    @GET("ejercicios2")
    fun getEjercicios(
    ): Call<EjerciciosResponse>

    @POST("rutinas2")
    fun postRutinas(
    ): Call<RutinaPopulateResponse>

    @GET("usuarios2")
    fun getUser(
        @Query("filters[id]") id: Int
    ): Call<UserResponse.Data>

}



