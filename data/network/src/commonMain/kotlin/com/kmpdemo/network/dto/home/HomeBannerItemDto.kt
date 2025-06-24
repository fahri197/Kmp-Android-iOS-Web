package com.kmpdemo.network.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeBannerItemDto(

    @SerialName("image")
    val image: String?,

    @SerialName("title")
    val title: String?

)