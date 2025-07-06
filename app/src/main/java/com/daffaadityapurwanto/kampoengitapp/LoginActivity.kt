package com.daffaadityapurwanto.kampoengitapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.daffaadityapurwanto.kampoengitapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Deklarasikan variabel untuk view binding agar bisa diakses di seluruh class
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding untuk layout activity_login.xml
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur listener untuk tombol login
        binding.loginButton.setOnClickListener {
            // Memanggil fungsi untuk validasi saat tombol ditekan
            validateAndLogin()
        }

        // Mengatur listener untuk link "Daftar di sini"
        binding.registerLinkText.setOnClickListener {
            Toast.makeText(this, "Membuka halaman pendaftaran...", Toast.LENGTH_SHORT).show()
            // Di sini Anda bisa membuat Intent untuk pindah ke RegisterActivity
            // val intent = Intent(this, RegisterActivity::class.java)
            // startActivity(intent)
        }

        // Menghapus pesan error secara otomatis saat pengguna mulai mengetik ulang
        setupErrorClearing()
    }

    /**
     * Fungsi untuk menghapus pesan error saat pengguna mengedit input field.
     */
    private fun setupErrorClearing() {
        binding.emailEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailInputLayout.error = null
        }
        binding.passwordEditText.doOnTextChanged { _, _, _, _ ->
            binding.passwordInputLayout.error = null
        }
    }

    /**
     * Fungsi untuk memvalidasi input email dan password.
     */
    private fun validateAndLogin() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        var isFormValid = true

        // 1. Validasi input email
        if (email.isEmpty()) {
            binding.emailInputLayout.error = "Email tidak boleh kosong"
            isFormValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInputLayout.error = "Format email tidak valid"
            isFormValid = false
        } else {
            binding.emailInputLayout.error = null
        }

        // 2. Validasi input password
        if (password.isEmpty()) {
            binding.passwordInputLayout.error = "Password tidak boleh kosong"
            isFormValid = false
        } else if (password.length < 6) {
            binding.passwordInputLayout.error = "Password minimal 6 karakter"
            isFormValid = false
        } else {
            binding.passwordInputLayout.error = null
        }

        // 3. Jika semua input valid, lanjutkan proses login
        if (isFormValid) {
            // --- LOGIKA LOGIN SEBENARNYA DITARUH DI SINI ---
            // Saat ini kita hanya menampilkan pesan sukses dan pindah halaman.
            // Di aplikasi nyata, di sini Anda akan berkomunikasi dengan server/database.

            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

            // Contoh pindah ke halaman utama (misal: HomeActivity)
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)

            // Tutup LoginActivity agar pengguna tidak bisa kembali ke sini dengan tombol back
            // finish()
        }
    }
}