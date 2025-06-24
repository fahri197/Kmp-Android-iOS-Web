package com.kmpdemo.network.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeServiceItemDto(

    @SerialName("id")
    val id: String?,

    @SerialName("image")
    val image: String?,

    @SerialName("title")
    val title: String?

)