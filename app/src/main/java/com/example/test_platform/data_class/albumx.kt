package com.example.test_platform.data_class

data class albumx(
    val album_type: String,
    val artists: List<artist>,
    val genres: List<Any>,
    val id: String,
    val images: List<image>,
    val label: String,
    val name: String,
    val popularity: Int,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: tracks,
    val type: String,
    val uri: String
)