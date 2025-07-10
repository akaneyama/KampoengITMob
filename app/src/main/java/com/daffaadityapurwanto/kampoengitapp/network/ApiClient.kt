package com.daffaadityapurwanto.kampoengitapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // PENTING: Ganti dengan alamat IP komputer Anda
    // Jika Anda menjalankan server Flask di komputer yang sama dengan emulator Android,
    // gunakan alamat IP khusus "10.0.2.2" untuk merujuk ke localhost komputer Anda.
    // Jangan gunakan "localhost" atau "127.0.0.1"
    private const val BASE_URL = "http://192.168.1.2:5000/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}