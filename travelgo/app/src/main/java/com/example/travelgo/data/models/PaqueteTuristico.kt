package com.example.travelgo.data.models

data class PaqueteTuristico(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val destino: String,
    val precio: Double,
    val imagenUrl: String
)
