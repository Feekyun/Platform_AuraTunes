package com.example.test_platform
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class login_page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page) // adjust if layout name is different

        val emailEditText = findViewById<EditText>(R.id.et_email)
        val passwordEditText = findViewById<EditText>(R.id.et_password)
        val loginButton = findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Optional: Add basic validation here
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Proceed to main dashboard
                val intent = Intent(this@login_page, dashboard_activity ::class.java)
                startActivity(intent)
                finish()
            } else {
                // Optional: Show error message (e.g., Toast)
            }
        }
    }
}