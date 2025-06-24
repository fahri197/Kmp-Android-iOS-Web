package com.kmpdemo.network.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeedItemDto(

    @SerialName("type")
    val type: String?,

    @SerialName("title")
    val title: String?,

    @SerialName("banners")
    val banners: List<HomeBannerItemDto>?,

    @SerialName("services")
    val services: List<HomeServiceItemDto>?

)