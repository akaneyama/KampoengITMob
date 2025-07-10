package com.daffaadityapurwanto.kampoengitapp.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login") // Menargetkan endpoint /login di server Anda
    suspend fun login(
        @Body request: LoginRequest // Mengirim data LoginRequest sebagai body JSON
    ): Response<LoginResponse> // Mengembalikan objek LoginResponse jika sukses
}