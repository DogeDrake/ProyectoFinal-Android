package com.example.unidad3_a


import com.google.gson.annotations.SerializedName

data class EjerciciosResponse(
    val `data`: List<Data>,
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
        data class Attributes(
            val createdAt: String,
            val ejercicionombre: String,
            val updatedAt: String
        )
    }

}