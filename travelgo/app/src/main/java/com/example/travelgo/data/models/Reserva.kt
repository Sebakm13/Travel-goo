package com.example.travelgo.data.models

data class Reserva(
    val id: Int,
    val paqueteId: Int,
    val userId: Int,
    val fecha: String,
    val estado: String
)
