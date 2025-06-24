package com.kmpdemo.domain.repositories

import com.kmpdemo.domain.entities.match.MatchResponse

interface MatchesRepository {
    suspend fun getLatestMatch(): Result<MatchResponse>
}