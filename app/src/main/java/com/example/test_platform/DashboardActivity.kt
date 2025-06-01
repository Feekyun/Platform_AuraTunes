package com.example.test_platform

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private val authToken = "Bearer BQDGiSMxexdtAdWe98ai7R4bBI3vls85NwrJUKwdwbX9_CUTaqmQWCZJW_h20ocig7mSw0ORdZ1gTY-RC4nr-jrbLXEy_8u6b3f2m_zVBjiNt9QG9CMHaLD9q2pHJC52gnW32-v5PIo\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)

        etSearch = findViewById(R.id.et_email)
        btnSearch = findViewById(R.id.btn_login)

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                searchTrack(query)
            } else {
                Toast.makeText(this, "Masukkan sesuatu untuk dicari!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchTrack(query: String) {
        RetrofitInstance.api.searchTracks(authToken, query).enqueue(object : Callback<SpotifyResponse> {
            override fun onResponse(call: Call<SpotifyResponse>, response: Response<SpotifyResponse>) {
                if (response.isSuccessful) {
                    val tracks = response.body()?.tracks?.items
                    tracks?.let {
                        for (track in it) {
                            Log.d("Spotify", "Track: ${track.name} - Artist: ${track.artists.joinToString { artist -> artist.name }}")
                        }
                        Toast.makeText(this@DashboardActivity, "Cek Logcat buat hasil!", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.e("Spotify", "Gagal: ${response.errorBody()?.string()}")
                    Toast.makeText(this@DashboardActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SpotifyResponse>, t: Throwable) {
                Log.e("Spotify", "Error: ${t.message}")
                Toast.makeText(this@DashboardActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
