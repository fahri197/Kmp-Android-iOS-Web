package com.kmpdemo.network.api

import com.kmpdemo.network.dto.home.HomeFeedResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class HomeApiServiceImpl(private val httpClient: HttpClient) : HomeApiService {

    override suspend fun fetchHomeFeed(
        page: Int,
        offset: Int
    ): Result<HomeFeedResponseDto> {
        return runCatching {
            httpClient.get("home/feed") {
                parameter("page", page)
                parameter("offset", offset)
            }.body<HomeFeedResponseDto>()
        }
    }
}