package com.kmpdemo.data.mappers

import com.kmpdemo.domain.entities.home.HomeServiceItem
import com.kmpdemo.network.dto.home.HomeServiceItemDto

object HomeServiceItemMapper {
    fun map(homeServiceItemDto: HomeServiceItemDto) = HomeServiceItem(
        id = homeServiceItemDto.id,
        image = homeServiceItemDto.image,
        title = homeServiceItemDto.title
    )
}