package com.kmpdemo.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponseDto(

    @SerialName("status")
    val status: String?,

    @SerialName("data")
    val data: List<MatchItemDto>?

)
