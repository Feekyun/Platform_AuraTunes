package com.example.test_platform.data_class

data class tracks(
    val items: List<item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)