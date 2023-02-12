package com.example.unidad3_a


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RutinaPopulateResponse(
    val `data`: List<Data>,
) : Serializable {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) : Serializable {
        data class Attributes(
            val createdAt: String,
            val ejercicios: Ejercicios,
            val privacidad: Boolean,
            val publishedAt: String,
            val titulorutina: String,
            val updatedAt: String,
            val usuario: Usuario
        ) : Serializable {
            data class Ejercicios(
                val `data`: List<Data>
            ) : Serializable {
                data class Data(
                    val attributes: Attributes,
                    val id: Int
                ) : Serializable {
                    data class Attributes(
                        val createdAt: String,
                        val ejercicionombre: String,
                        val updatedAt: String
                    ) : Serializable
                }
            }

            data class Usuario(
                val `data`: Data
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
        }
    }
}