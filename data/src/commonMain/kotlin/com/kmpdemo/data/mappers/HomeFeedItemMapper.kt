package com.kmpdemo.data.mappers

import com.kmpdemo.domain.entities.home.HomeFeedItem
import com.kmpdemo.domain.entities.home.HomeFeedItemType
import com.kmpdemo.network.dto.home.HomeFeedItemDto

object HomeFeedItemMapper {
    fun map(homeFeedItemDto: HomeFeedItemDto) = when (homeFeedItemDto.type) {
        HomeFeedItemType.BANNER -> HomeFeedItem.BannerRow(
            title = homeFeedItemDto.title.orEmpty(),
            banners = homeFeedItemDto.banners?.mapNotNull { itemDto ->
                HomeBannerItemMapper.map(itemDto)
            } ?: emptyList()
        )

        HomeFeedItemType.SERVICES -> HomeFeedItem.ServicesGrid(
            title = homeFeedItemDto.title.orEmpty(),
            services = homeFeedItemDto.services?.mapNotNull { itemDto ->
                HomeServiceItemMapper.map(itemDto)
            } ?: emptyList()
        )
        else -> null
    }
}