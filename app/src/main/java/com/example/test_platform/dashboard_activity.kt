package com.example.test_platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.test_platform.fragments.home
import com.example.test_platform.fragments.progress_bar

class dashboard_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Status bar putih dan ikon hitam
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true

        setContentView(R.layout.dashboard_activity)

        // Initialize and show progress bar fragment
        val progressBarFragment = progress_bar()
        AddFragment(progressBarFragment)

        fetchDataForHome()
    }

    private fun AddFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_frame, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun fetchDataForHome() {
        android.os.Handler().postDelayed({
            AddFragment(home())
        }, 2000) // Delay 2 detik
    }
}
