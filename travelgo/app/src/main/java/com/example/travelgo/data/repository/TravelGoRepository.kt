package com.example.travelgo.data.repository

import com.example.travelgo.data.api.ApiClient
import com.example.travelgo.data.models.AuthResponse
import com.example.travelgo.data.models.PaqueteTuristico
import com.example.travelgo.data.models.Reserva

class TravelGoRepository {

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val resp = ApiClient.api.login(mapOf("email" to email, "password" to password))
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProfile(token: String): Result<AuthResponse> {
        return try {
            val resp = ApiClient.api.getProfile("Bearer $token")
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPaquetes(): Result<List<PaqueteTuristico>> {
        return try {
            val resp = ApiClient.api.getPaquetes()
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun crearReserva(token: String, paqueteId: Int, fecha: String): Result<Reserva> {
        return try {
            val resp = ApiClient.api.crearReserva(
                "Bearer $token",
                mapOf("paqueteId" to paqueteId, "fecha" to fecha)
            )
            Result.success(resp)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
