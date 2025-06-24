package com.kmpdemo.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamItemDto(

    @SerialName("name")
    val name: String?,

    @SerialName("shortname")
    val shortname: String?,

    @SerialName("img")
    val img: String?,

)