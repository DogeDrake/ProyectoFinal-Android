package com.example.unidad3_a


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(
    val `data`: List<Data>,
) : Serializable {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) : Serializable {
        data class Attributes(
            val altura: Int,
            val contrasena: String,
            val createdAt: String,
            val email: String,
            val imc: Int,
            val nacimiento: String,
            val nombre: String,
            val peso: Int,
            val publishedAt: String,
            val updatedAt: String,
            val username: String
        ) : Serializable
    }
}