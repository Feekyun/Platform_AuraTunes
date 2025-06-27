package com.example.test_platform

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // ✅ Set warna putih untuk status bar
        window.statusBarColor = getColor(R.color.black)

        // ✅ Biar ikon status bar (jam, baterai, sinyal) jadi hitam
        WindowCompat.getInsetsController(window, window.decorView)
            ?.isAppearanceLightStatusBars = true

        setContentView(R.layout.splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, login_page::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}
