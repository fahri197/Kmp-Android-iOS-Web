package com.kmpdemo.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchItemDto(

    @SerialName("date")
    val date: String?,

    @SerialName("venue")
    val venue: String?,

    @SerialName("score")
    val score: List<ScoreItemDto>?,

    @SerialName("teamInfo")
    val teamInfo: List<TeamItemDto>?,

    @SerialName("matchType")
    val matchType: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("status")
    val status: String?,

)