package com.kmpdemo.domain.entities.home

data class HomeFeedResponse(
    val success: Boolean,
    val message: String?,
    val data: List<HomeFeedItem> = emptyList()
)