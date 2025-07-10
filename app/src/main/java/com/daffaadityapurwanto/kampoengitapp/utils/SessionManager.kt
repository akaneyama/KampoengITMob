package com.daffaadityapurwanto.kampoengitapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("KampoengITAppPrefs", Context.MODE_PRIVATE)

    companion object {
        const val AUTH_TOKEN = "auth_token"
    }

    /**
     * Fungsi untuk menyimpan token otentikasi
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    /**
     * Fungsi untuk mengambil token otentikasi
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(AUTH_TOKEN, null)
    }
}