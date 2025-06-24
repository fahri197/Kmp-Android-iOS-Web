package com.kmpdemo.data.repositoriesImpl

import com.kmpdemo.data.mappers.HomeFeedResponseMapper
import com.kmpdemo.domain.entities.home.HomeFeedResponse
import com.kmpdemo.domain.repositories.HomeRepository
import com.kmpdemo.network.api.HomeApiServiceImpl

internal class HomeRepositoryImpl(
    private val homeApiServices: HomeApiServiceImpl
) : HomeRepository {

    override suspend fun fetchHomeFeed(page: Int, offset: Int): Result<HomeFeedResponse> {
        return homeApiServices.fetchHomeFeed(
            page = page,
            offset = offset
        ).mapCatching {
            HomeFeedResponseMapper.map(it)
        }
    }
}
