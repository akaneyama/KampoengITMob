package com.daffaadityapurwanto.kampoengitapp.network

import com.google.gson.annotations.SerializedName

// Data yang akan kita KIRIM ke API
data class LoginRequest(
    val username: String,
    val password: String
)

// Data yang akan kita TERIMA dari API jika sukses
data class LoginResponse(
    @SerializedName("access_token") // Mencocokkan nama field di JSON dari Flask
    val accessToken: String
)

// Data yang akan kita TERIMA jika ada error dari API
data class ErrorResponse(
    val msg: String
)