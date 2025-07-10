package com.daffaadityapurwanto.kampoengitapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.daffaadityapurwanto.kampoengitapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    // Deklarasikan variabel untuk view binding
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding untuk layout activity_register.xml
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur listener untuk tombol register
        binding.registerButton.setOnClickListener {
            validateAndRegister()
        }

        // Mengatur listener untuk link "Masuk di sini"
        binding.loginLinkText.setOnClickListener {
            finish()
        }

        setupErrorClearing()
    }

    /**
     * Fungsi untuk menghapus pesan error saat pengguna mengedit setiap input field.
     */
    private fun setupErrorClearing() {
        binding.phoneEditText.doOnTextChanged { _, _, _, _ ->
            binding.phoneInputLayout.error = null
        }
        binding.emailEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailInputLayout.error = null
        }

    }

    /**
     * Fungsi untuk memvalidasi semua input pada form pendaftaran.
     */
    private fun validateAndRegister() {
        val name = binding.phoneEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        var isFormValid = true

        // 1. Validasi input Nama
        if (name.isEmpty()) {
            binding.phoneInputLayout.error = "Nomor tidak boleh kosong"
            isFormValid = false
        } else {
            binding.phoneInputLayout.error = null
        }

        // 2. Validasi input Email
        if (email.isEmpty()) {
            binding.emailInputLayout.error = "Email tidak boleh kosong"
            isFormValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInputLayout.error = "Format email tidak valid"
            isFormValid = false
        } else {
            binding.emailInputLayout.error = null
        }



        // 4. Jika semua input valid, lanjutkan proses pendaftaran
        if (isFormValid) {
            // --- LOGIKA REGISTER SEBENARNYA DITARUH DI SINI ---
            // Di sini Anda akan mengirim data (nama, email, password) ke server/database.
            // Untuk saat ini, kita hanya menampilkan pesan sukses.

            Toast.makeText(this, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show()

            // Setelah berhasil, biasanya pengguna akan diarahkan ke halaman login
            // untuk masuk dengan akun yang baru dibuat.
            // val intent = Intent(this, LoginActivity::class.java)
            // startActivity(intent)

            // Tutup RegisterActivity
            finish()
        }
    }
}