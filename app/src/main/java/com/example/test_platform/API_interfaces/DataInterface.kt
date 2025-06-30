package com.example.test_platform.API_interfaces

import com.example.test_platform.data_class.search.data
import com.example.test_platform.data_class.album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DataInterface {
    @Headers(
        "X-RapidAPI-Key: 9ca8761750msh99db197d603a12bp1cb771jsn0698074b172d",
        "X-RapidAPI-Host: spotify23.p.rapidapi.com"
    )
    @GET("albums")
    fun getAlbumData(@Query("ids") query: String): Call<album>

    @GET("tracks/")
    fun getTrackDetails(@Query("ids") trackId: String): Call<com.example.test_platform.data_class.tracks>



    @Headers(
        "X-RapidAPI-Key: 9ca8761750msh99db197d603a12bp1cb771jsn0698074b172d",
        "X-RapidAPI-Host: spotify23.p.rapidapi.com"
    )
    @GET("search/")
    fun getSearchResults(
        @Query("q") query: String,
        @Query("type") type: String = "tracks",
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 8,
        @Query("numberOfTopResults") numberOfTopResults: Int = 5
    ): Call<data>
}