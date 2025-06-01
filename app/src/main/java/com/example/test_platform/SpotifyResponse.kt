package com.example.test_platform

data class SpotifyResponse(
    val tracks: Tracks
)

data class Tracks(
    val items: List<Track>
)

data class Track(
    val name: String,
    val artists: List<Artist>
)

data class Artist(
    val name: String
)
