package com.kmpdemo.domain.repositories

import com.kmpdemo.domain.entities.home.HomeFeedResponse

interface HomeRepository {
    suspend fun fetchHomeFeed(page: Int, offset: Int): Result<HomeFeedResponse>
}