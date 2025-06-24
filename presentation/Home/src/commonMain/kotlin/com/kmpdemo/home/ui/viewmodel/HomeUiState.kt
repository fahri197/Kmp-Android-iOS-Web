package com.kmpdemo.home.ui.viewmodel

import com.kmpdemo.domain.entities.home.HomeFeedItem

data class HomeUiState(
    val feedItems: List<HomeFeedItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true
)