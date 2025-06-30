package com.example.test_platform.data_class.search
data class tracks(
    val items: List<item>,
    val pagingInfo: paginginfo,
    val totalCount: Int
)