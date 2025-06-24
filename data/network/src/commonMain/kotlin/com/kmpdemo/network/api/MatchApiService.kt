package com.kmpdemo.network.api

import com.kmpdemo.network.dto.MatchResponseDto

interface MatchApiService {
    suspend fun getCurrentMatch(apikey: String): Result<MatchResponseDto>

}