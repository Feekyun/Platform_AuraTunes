package com.example.test_platform

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.test_platform.fragments.home
import com.example.test_platform.fragments.progress_bar

class dashboard_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true

        setContentView(R.layout.dashboard_activity)

        // Show progress bar fragment
        val progressBarFragment = progress_bar()
        AddFragment(progressBarFragment)

        // Delay to show home fragment
        fetchDataForHome()

        val homeIcon = findViewById<ImageView>(R.id.home_icon)
        val searchIcon = findViewById<ImageView>(R.id.srch_icn)

        homeIcon.setOnClickListener {
            replaceFragment(home())
        }

        searchIcon.setOnClickListener {
            replaceFragment(com.example.test_platform.fragments.search())
        }
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
        }, 2000)
    }
}
