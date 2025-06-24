package com.kmpdemo.network.api

import com.kmpdemo.network.dto.home.HomeFeedResponseDto

interface HomeApiService {
    suspend fun fetchHomeFeed(page: Int, offset: Int): Result<HomeFeedResponseDto>
}