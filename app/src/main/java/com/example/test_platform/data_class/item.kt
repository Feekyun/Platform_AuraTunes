package com.example.test_platform.data_class

data class item(
    val artists: List<artist>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val name: String,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)