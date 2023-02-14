package com.example.unidad3_a


import com.google.gson.annotations.SerializedName

data class RegistroResponse(
    val jwt: String,
    val user: User
) {
    data class User(
        val altura: Any,
        val apellidos: String,
        val blocked: Boolean,
        val confirmed: Boolean,
        val createdAt: String,
        val email: String,
        val fechaNacimiento: String,
        val id: Int,
        val imc: Any,
        val peso: Any,
        val provider: String,
        val updatedAt: String,
        val username: String
    )
}