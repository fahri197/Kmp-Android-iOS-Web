package com.kmpdemo.data.repositoriesImpl

import com.kmpdemo.data.mappers.MatchResponseMapper
import com.kmpdemo.domain.repositories.MatchesRepository
import com.kmpdemo.domain.entities.match.MatchResponse
import com.kmpdemo.network.api.MatchApiService

internal class MatchRepositoryImpl(
    private val matchApiServices: MatchApiService,
    private val matchApiKey: String
) : MatchesRepository {

    override suspend fun getLatestMatch(): Result<MatchResponse> {
        return matchApiServices.getCurrentMatch(apikey = matchApiKey).mapCatching {
            MatchResponseMapper.map(it)
        }
    }
}
