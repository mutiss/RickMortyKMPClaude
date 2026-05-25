package com.mutissx.rickmortykmpclaude.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("air_date") val airDate: String,
    @SerialName("episode") val episode: String
)
