package com.kmpdemo.network.api

import com.kmpdemo.network.dto.MatchResponseDto
import com.kmpdemo.network.dto.home.HomeFeedResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MatchApiServiceImpl(private val httpClient: HttpClient) : MatchApiService {

    override suspend fun getCurrentMatch(apikey: String): Result<MatchResponseDto> {
        return runCatching {
            httpClient.get("home/feed") {
                parameter("apikey", apikey)
            }.body<MatchResponseDto>()
        }
    }
}