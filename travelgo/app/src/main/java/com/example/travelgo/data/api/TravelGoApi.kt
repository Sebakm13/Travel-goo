package com.example.travelgo.data.api

import com.example.travelgo.data.models.AuthResponse
import com.example.travelgo.data.models.PaqueteTuristico
import com.example.travelgo.data.models.Reserva
import retrofit2.http.*

interface TravelGoApi {

    @POST("/auth/login")
    suspend fun login(@Body body: Map<String, String>): AuthResponse

    @POST("/auth/signup")
    suspend fun signup(@Body body: Map<String, String>): AuthResponse

    @GET("/auth/me")
    suspend fun getProfile(@Header("Authorization") token: String): AuthResponse

    @GET("/paquetes")
    suspend fun getPaquetes(): List<PaqueteTuristico>

    @POST("/reservas")
    suspend fun crearReserva(
        @Header("Authorization") token: String,
        @Body body: Map<String, Any>
    ): Reserva
}
