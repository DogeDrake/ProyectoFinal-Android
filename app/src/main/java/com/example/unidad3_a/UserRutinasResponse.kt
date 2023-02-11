package com.example.unidad3_a


import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class UserRutinasResponse(
    val `data`: List<Data>,
):Serializable {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ):Serializable {
        data class Attributes(
            val createdAt: String,
            val privacidad: Boolean,
            val publishedAt: String,
            val titulorutina: String,
            val updatedAt: String
        ):Serializable
    }

}