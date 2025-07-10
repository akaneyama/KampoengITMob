package com.daffaadityapurwanto.kampoengitapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.daffaadityapurwanto.kampoengitapp.databinding.ActivityLoginBinding
import com.daffaadityapurwanto.kampoengitapp.network.ApiClient
import com.daffaadityapurwanto.kampoengitapp.network.LoginRequest
import com.daffaadityapurwanto.kampoengitapp.utils.SessionManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.daffaadityapurwanto.kampoengitapp.network.ErrorResponse
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SessionManager
        sessionManager = SessionManager(this)

        binding.loginButton.setOnClickListener {
            validateAndLogin()
        }

        binding.registerLinkText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // ... (fungsi setupErrorClearing() tetap sama)

    private fun validateAndLogin() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        // Validasi client-side (opsional, karena validasi utama ada di sini)
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        // Tampilkan loading dan nonaktifkan tombol
        binding.loginButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        // Gunakan lifecycleScope untuk menjalankan coroutine
        lifecycleScope.launch {
            try {
                // Buat request body
                val loginRequest = LoginRequest(username = email, password = password)

                // Panggil API
                val response = ApiClient.instance.login(loginRequest)

                // Cek hasil response
                if (response.isSuccessful) {
                    // Jika sukses (kode 2xx)
                    val accessToken = response.body()?.accessToken
                    if (accessToken != null) {
                        // Simpan token
                        sessionManager.saveAuthToken(accessToken)

                        Toast.makeText(this@LoginActivity, "Login Berhasil!", Toast.LENGTH_LONG).show()

                        // Pindah ke halaman utama
                        // val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        // startActivity(intent)
                        // finish() // Tutup activity ini
                    }
                } else {
                    // Jika gagal (kode 4xx atau 5xx)
                    val errorBody = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson<ErrorResponse>(errorBody, object : TypeToken<ErrorResponse>() {}.type)
                    Toast.makeText(this@LoginActivity, errorResponse.msg, Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
                // Tangani error koneksi (misal: tidak ada internet)
                Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                // Sembunyikan loading dan aktifkan kembali tombol
                binding.loginButton.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}