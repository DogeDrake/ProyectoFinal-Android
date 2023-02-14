package com.example.unidad3_a

import retrofit2.Call
import retrofit2.Response
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

    //potater
    @GET("ejercicios2")
    fun getEjercicios(
    ): Call<EjerciciosResponse>

    @POST("rutinas2")
    fun postRutinas(
    ): Call<RutinaPopulateResponse>

    @GET("usuarios2")
    fun getUser(
        @Query("filters[id]") id: String
    ): Call<UserResponse>

    @POST("auth/local/register")
    fun meterUser(
        @Body request: DatosRegister
    ): Call<RegistroResponse>

    @POST("auth/local")
    fun loginUser(
        @Body request: DatosLogin
    ): Call<LoginResponse>

    data class DatosLogin(val email: String, val password: String)
    data class DatosRegister(
        val email: String,
        val password: String,
        val fechaNacimiento: String,
        val apellidos: String,
        val username: String
    )

    @GET("rutinas2?populate=*")
    fun getUserRutinesPopualteFiltroUser(
        @Query("filters[id]") id: String
    ): Call<RutinaPopulateResponse>

    @PUT("my-endpoint")
    suspend fun updateData(@Body data: UserResponse.Data): Response<UserResponse.Data>

    @DELETE("usuarios2/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Unit>
}
