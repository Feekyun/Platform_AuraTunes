package com.example.test_platform.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_platform.API_interfaces.DataInterface
import com.example.test_platform.R
import com.example.test_platform.adapters.search_adapter
import com.example.test_platform.data_class.search.data
import com.example.test_platform.data_class.search.item
import com.example.test_platform.data_class.tracks
import com.example.test_platform.player_activity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class search : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var searchText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var retrofitBuilder: DataInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchView = view.findViewById(R.id.Search_box)
        searchText = view.findViewById(R.id.search_query)
        recyclerView = view.findViewById(R.id.rcy_search_track)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchText.visibility = View.GONE

        retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://spotify23.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataInterface::class.java)

        setupSearchView()
        return view
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    performSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun performSearch(query: String) {
        searchText.visibility = View.VISIBLE
        searchText.text = getString(R.string.search_result_prefix, query)

        retrofitBuilder.getSearchResults(query).enqueue(object : Callback<data> {
            override fun onResponse(call: Call<data>, response: Response<data>) {
                if (response.isSuccessful && response.body() != null) {
                    val items: List<item> = response.body()!!.tracks.items

                    val adapter = search_adapter(requireActivity(), items) { selectedTrackDatax ->
                        val trackId = selectedTrackDatax.id

                        retrofitBuilder.getTrackDetails(trackId).enqueue(object : Callback<tracks> {
                            override fun onResponse(call: Call<tracks>, response: Response<tracks>) {
                                val previewUrl = response.body()?.items?.firstOrNull()?.preview_url

                                if (!previewUrl.isNullOrEmpty()) {
                                    val intent = Intent(requireContext(), player_activity::class.java).apply {
                                        putExtra("track_name", selectedTrackDatax.name)
                                        putExtra("artist_name", selectedTrackDatax.artists.items.joinToString(", ") { it.profile.name })
                                        putExtra("album_image", selectedTrackDatax.albumoftrack.coverArt.sources.firstOrNull()?.url)
                                        putExtra("Song", previewUrl)
                                        putExtra("album_name", selectedTrackDatax.albumoftrack.name)
                                        putExtra("duration_ms", selectedTrackDatax.duration.totalMilliseconds)
                                    }
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(requireContext(), "No preview available", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<tracks>, t: Throwable) {
                                Toast.makeText(requireContext(), "Failed to load preview", Toast.LENGTH_SHORT).show()
                                Log.e("TrackFetch", "Error: ${t.message}")
                            }
                        })
                    }

                    recyclerView.adapter = adapter
                } else {
                    Log.e("SearchFragment", "Response failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<data>, t: Throwable) {
                Log.e("SearchFragment", "API call failed: ${t.message}")
            }
        })
    }
}
