package com.kmpdemo.data.mappers

import com.kmpdemo.domain.entities.home.HomeBannerItem
import com.kmpdemo.network.dto.home.HomeBannerItemDto

object HomeBannerItemMapper {
    fun map(homeBannerItemDto: HomeBannerItemDto) = HomeBannerItem(
        image = homeBannerItemDto.image,
        title = homeBannerItemDto.title
    )
}