package com.kmpdemo.usecase.home

import com.kmpdemo.domain.entities.home.HomeFeedResponse
import com.kmpdemo.domain.repositories.HomeRepository

internal class HomeFeedUseCaseImpl(
    private val homeRepository: HomeRepository,
) : HomeFeedUseCase {

    override suspend fun execute(input: HomeFeedUseCase.Input): Result<HomeFeedResponse> =
        Result.runCatching {
            homeRepository.fetchHomeFeed(page = input.page, offset = input.offset).getOrThrow()
        }

}