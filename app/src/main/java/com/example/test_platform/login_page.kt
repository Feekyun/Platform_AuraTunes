package com.example.test_platform

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.content.ContextCompat
import com.example.test_platform.databinding.LoginPageBinding

class login_page : AppCompatActivity() {

    private lateinit var binding: LoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Ubah status bar jadi putih & ikon status bar jadi gelap
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true

        // Inisialisasi view binding
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol login
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        // Navigasi ke halaman register (jika kamu aktifkan nanti)
        // binding.tvRegister.setOnClickListener {
        //     val intent = Intent(this, RegisterPageActivity::class.java)
        //     startActivity(intent)
        // }
    }

    private fun handleLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (validateInput(email, password)) {
            // TODO: Tambahkan logika autentikasi (misalnya ke Firebase, API, dll)
            openDashboard()
        }
    }

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

        // Validasi tambahan jika mau
        // if (password.length < 8) {
        //     binding.etPassword.error = "Password minimal 8 karakter"
        //     binding.etPassword.requestFocus()
        //     return false
        // }

        return true
    }

    private fun openDashboard() {
        val intent = Intent(this, dashboard_activity::class.java)
        startActivity(intent)
        finish()
    }
}