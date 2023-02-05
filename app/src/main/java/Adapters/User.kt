package Adapters

import java.io.Serializable

data class user(
    val `data`: List<Data>
) : Serializable {
    data class Data(
        val name: String,
        val surname: String,
        val exer: List<Ejercicios>
    ) : Serializable {
        data class Ejercicios(
            val nombre: String,
            val repeticiones: String
        ) : Serializable
    }
}