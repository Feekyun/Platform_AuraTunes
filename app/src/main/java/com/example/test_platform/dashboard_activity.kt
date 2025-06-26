package com.example.test_platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test_platform.fragments.home
import com.example.test_platform.fragments.progress_bar

class dashboard_activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        // Initialize progress bar fragment
        lateinit var progressBarFragment: Fragment
        progressBarFragment = progress_bar()

        // Show progress bar initially
        AddFragment(progressBarFragment)

        fetchDataForHome()
    }

    private fun AddFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTraction = fragmentManager.beginTransaction()
        fragmentTraction.add(R.id.fragment_frame, fragment)
        fragmentTraction.commit()
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
        }, 2000) // Delay for 2 seconds
    }
}
