package com.kmpdemo.network.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeedResponseDto(

    @SerialName("success")
    val success: Boolean?,

    @SerialName("message")
    val message: String?,

    @SerialName("data")
    val data: List<HomeFeedItemDto>?

)
