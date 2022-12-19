package com.example.ut5ej6gonzalezcristian

data class Vehiculo(
    val dni: String,
    val nombre: String,
    val email: String,
    val matricula: String,
    val modeloCoche: String,
    val fechaEntrega: String,
    val observaciones: String,
    var estado: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vehiculo

        if (matricula != other.matricula) return false

        return true
    }

    override fun hashCode(): Int {
        return matricula.hashCode()
    }
}