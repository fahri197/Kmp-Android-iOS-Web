package com.kmpdemo.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreItemDto(

    @SerialName("r")
    val r: Int?,

    @SerialName("w")
    val w: Int?,

    @SerialName("inning")
    val inning: String?,

    @SerialName("o")
    val o: Double?

)
