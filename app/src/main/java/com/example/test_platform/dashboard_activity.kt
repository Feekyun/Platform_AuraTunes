package com.example.test_platform

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class dashboard_activity : AppCompatActivity() {

    private lateinit var homeIcon: ImageView
    private lateinit var searchIcon: ImageView
    private lateinit var libraryIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        // Inisialisasi view
        homeIcon = findViewById(R.id.home_icon)
        searchIcon = findViewById(R.id.srch_icn)
        libraryIcon = findViewById(R.id.libr_icn)

        // Default fragment saat pertama kali dibuka
        replaceFragment(HomeFragment())

        // Navigasi klik
        homeIcon.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        searchIcon.setOnClickListener {
            replaceFragment(SearchFragment())
        }

        libraryIcon.setOnClickListener {
            replaceFragment(LibraryFragment())
        }
    }

    // Fungsi ganti fragment
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_frame, fragment)
        transaction.commit()
    }
}
