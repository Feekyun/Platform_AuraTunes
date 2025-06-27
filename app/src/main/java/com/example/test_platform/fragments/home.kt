package com.example.test_platform.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_platform.API_interfaces.DataInterface
import com.example.test_platform.data_class.album
import com.example.test_platform.fragments.history
import com.example.test_platform.fragments.notification
import com.example.test_platform.fragments.setting
import com.example.test_platform.R
import com.example.test_platform.adapters.albadapter
import com.example.test_platform.adapters.albs_adapter
import com.example.test_platform.adapters.pladapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

class home()  : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var myadapter: albadapter
    lateinit var pl_rcv:RecyclerView
    lateinit var pl_adapter:pladapter
    lateinit var hits_rcy:RecyclerView
    lateinit var albumsadp:albs_adapter
    lateinit var mash_rcy:RecyclerView
    lateinit var hiph:RecyclerView
    lateinit var reco:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val greeting = view.findViewById<TextView>(R.id.greeting)
        val notification=view.findViewById<ImageView>(R.id.notifications)
        val history=view.findViewById<ImageView>(R.id.history)
        val gear=view.findViewById<ImageView>(R.id.setting)

        recyclerView=view.findViewById(R.id.rcy_albums)
        pl_rcv=view.findViewById(R.id.rcy_playlists)
        hits_rcy=view.findViewById(R.id.hits24)
        mash_rcy=view.findViewById(R.id.mashups20)
        reco=view.findViewById(R.id.rfy)

        //Albums Titles
        val txt_artist=view.findViewById<TextView>(R.id.artists)
        val txt_hits=view.findViewById<TextView>(R.id.hits)
        val txt_mashup=view.findViewById<TextView>(R.id.mashup)
        val txt_rcy=view.findViewById<TextView>(R.id.rcy)


        // Initialize RecyclerViews
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        pl_rcv.layoutManager = GridLayoutManager(requireContext(), 2)

        hits_rcy.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mash_rcy.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        reco.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Get the current time
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        // Update greeting text based on the current time
        val greetingText = when (hourOfDay) {
            in 0..3 -> "Good morning" // 12 AM to 3 AM
            in 4..11 -> "Good morning"
            in 12..15 -> "Good afternoon"
            in 16..23 -> "Good evening"
            else -> "NULL"
        }
        greeting.text = greetingText


        notification.setOnClickListener {
            val fragment =notification()
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        history.setOnClickListener {
            val fragment =history()
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        gear.setOnClickListener {
            val fragment =setting()
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        //Populating the Data
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://spotify23.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataInterface::class.java)


        //fetch Grid Playlists
        val playlistid= listOf(
            "20r762YmB5HeofjMCiPMLv", //Kanye
            "7vwi3kXdpkaRO3if4N2gBN", //IVE
            "5zi7WsKlIiUXv09tbGLKsE", //Tyler
            "1qOD7pel3w9en2JKQ3l6Ha", //NMIXX
            "18NOKLkZETa4sWwLMIm0UZ", //Travis
            "3gHhPm8z8tid1kvpniUKuK" //Aespa
        )
        val playlistidStr=playlistid.joinToString(",")

        val PlaylistData = retrofitBuilder.getAlbumData(playlistidStr)


        PlaylistData.enqueue(object : Callback<album?> {
            override fun onResponse(call: Call<album?>, response: Response<album?>) {
                val dataList = response.body()?.albums ?: emptyList()
                pl_adapter = pladapter(requireActivity(), dataList)
                pl_rcv.adapter =pl_adapter
            }

            override fun onFailure(call: Call<album?>, t: Throwable) {
                //On  API failure
                Log.e("ERROR", "Failure " + t.message)
            }
        })

        //Fetch Top artist Albums Data
        val albumIds = listOf(
            "5agEAxt8vks5Xk0NfEbI5D", //bernadya
            "1HMLpmZAnNyl9pxvOnTovV", //Newjeans
            "1hmlhl74JfLyUqmqtCwvFb", //Laufey
            "2Ek1q2haOnxVqhvVKqMvJe", //Kanye
            "1KNUCVXgIxKUGiuEB8eG0i", //Clairo
            "0U28P0QVB1QRxpqp5IHOlH", //Tyler
            "7aJuG4TFXa2hmE4z1yxc3n", //Billie
            "12iBcJgeaPMhrO9YlOaMba" //YungKai
        )
        val albumIdsString = albumIds.joinToString(",")
        val retrofitDataAlbum = retrofitBuilder.getAlbumData(albumIdsString)


        retrofitDataAlbum.enqueue(object : Callback<album?> {
            override fun onResponse(call: Call<album?>, response: Response<album?>) {
                val dataList = response.body()?.albums ?: emptyList()
                myadapter = albadapter(requireActivity(), dataList)
                recyclerView.adapter = myadapter
                txt_artist.visibility=View.VISIBLE
            }

            override fun onFailure(call: Call<album?>, t: Throwable) {
                //On  API failure
                Log.e("ERROR", "Failure " + t.message)
            }
        })

        //2024 hits
        val hits_id = listOf("75KyCmWatZRvlMcMRed9BG",
            "6XVW3zoK1nmskW7drvHy9h",
            "0TMmGHybMKtsjBBEmwXHkF",
            "1Rey8BJT0RtzfDfxGjnp23",
            "1DAuVHMlBvIjzWZALSUXbn",
            "55Rgrt92qQCdUgtDJhvsPG")
        val hitsid_string=hits_id.joinToString(",")

        val retrofithits=retrofitBuilder.getAlbumData(hitsid_string)

        retrofithits.enqueue(object :Callback<album?> {
            override fun onResponse(call: Call<album?>, response: Response<album?>) {
                val dL = response.body()?.albums ?: emptyList()
                albumsadp = albs_adapter(requireActivity(), dL)
                hits_rcy.adapter = albumsadp
                txt_hits.visibility=View.VISIBLE
            }
            override fun onFailure(call: Call<album?>, t: Throwable) {
                //On  API failure
                Log.e("ERROR", "Failure " + t.message)
            }
        })

        //20's Mashups
        val mashup20=listOf("0FZK97MXMm5mUQ8mtudjuK", //Black Parade
            "3DuiGV3J09SUhvp8gqNx8h", //Three Cheers
            "4SZko61aMnmgvNhfhgTuD3", //Graduation
            "5pTq5IXRivno3gGATveA4z", //Bintang Di Surga
            "0H4ELcHequ3OligrjWfrZP", //So Wrong
            "3svp0TRDC5MMUAPNMimAi1", //Wonder Years
            "0gwS2D9sukMLXNvleEnYr2" //For Lovers
        )
        val mash_string=mashup20.joinToString(",")
        val retrofit_mash=retrofitBuilder.getAlbumData(mash_string)

        retrofit_mash.enqueue(object :Callback<album?> {
            override fun onResponse(call: Call<album?>, response: Response<album?>) {
                val dL = response.body()?.albums ?: emptyList()
                albumsadp = albs_adapter(requireActivity(), dL)
                mash_rcy.adapter = albumsadp
                txt_mashup.visibility=View.VISIBLE
            }
            override fun onFailure(call: Call<album?>, t: Throwable) {
                //On  API failure
                Log.e("ERROR", "Failure " + t.message)
            }
        })

        // recommended
        val rec_uid=listOf("4SVd1APxtjPLbghqMzqmBq",
            "5Y5dkZeSlePrTopuETspAh",
            "56xGIqUN2Xj6jKsj88SUnZ",
            "1pCA38N6MkLlthXtAOvZTU",
            "3QKXWxY09gFSdsOD9JWJDl"
        )
        val rec_string=rec_uid.joinToString(",")
        val retro_recu=retrofitBuilder.getAlbumData(rec_string)
        retro_recu.enqueue(object :Callback<album?> {
            override fun onResponse(call: Call<album?>, response: Response<album?>) {
                val dL = response.body()?.albums ?: emptyList()
                albumsadp = albs_adapter(requireActivity(), dL)
                reco.adapter = albumsadp
                txt_rcy.visibility=View.VISIBLE
            }

            override fun onFailure(call: Call<album?>, t: Throwable) {
                //On  API failure
                Log.e("ERROR", "Failure " + t.message)
            }
        })

        return view
    }
}