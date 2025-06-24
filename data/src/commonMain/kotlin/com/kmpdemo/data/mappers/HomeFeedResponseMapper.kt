package com.kmpdemo.data.mappers

import com.kmpdemo.domain.entities.home.HomeFeedResponse
import com.kmpdemo.network.dto.home.HomeFeedResponseDto

object HomeFeedResponseMapper {
    fun map(homeFeedResponseDto: HomeFeedResponseDto) = HomeFeedResponse(
        success = homeFeedResponseDto.success == true,
        message = homeFeedResponseDto.message,
        data = homeFeedResponseDto.data?.mapNotNull { itemDto ->
            HomeFeedItemMapper.map(itemDto)
        } ?: emptyList()
    )
}