package com.example.test_platform

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test_platform.databinding.LoginPageBinding // Import kelas binding yang digenerate

class login_page : AppCompatActivity() {

    // Deklarasikan variabel binding
    private lateinit var binding: LoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener pada tombol login
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        // Tambahan: Contoh navigasi ke halaman register
        // binding.tvRegister.setOnClickListener {
        //     val intent = Intent(this, RegisterPageActivity::class.java)
        //     startActivity(intent)
        // }
    }

    /**
     * Fungsi untuk menangani logika saat tombol login ditekan.
     */
    private fun handleLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (validateInput(email, password)) {
            // TODO: Tambahkan logika autentikasi dengan backend di sini

            // Jika validasi berhasil, lanjutkan ke dashboard
            openDashboard()
        }
    }

    /**
     * Fungsi untuk memvalidasi input email dan password dari pengguna.
     * @return true jika input valid, false jika tidak.
     */
    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmail.error = "Email tidak boleh kosong"
            binding.etEmail.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Format email tidak valid"
            binding.etEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            binding.etPassword.requestFocus()
            return false
        }

        // Tambahan: Validasi panjang password
        // if (password.length < 8) {
        //     binding.etPassword.error = "Password minimal 8 karakter"
        //     binding.etPassword.requestFocus()
        //     return false
        // }

        return true
    }

    /**
     * Fungsi untuk berpindah ke halaman Dashboard.
     */
    private fun openDashboard() {
        // Ganti DashboardActivity::class.java dengan nama activity dashboard Anda yang sebenarnya
        val intent = Intent(this, dashboard_activity::class.java)
        startActivity(intent)
        finish() // Menutup activity login agar tidak bisa kembali dengan tombol back
    }
}