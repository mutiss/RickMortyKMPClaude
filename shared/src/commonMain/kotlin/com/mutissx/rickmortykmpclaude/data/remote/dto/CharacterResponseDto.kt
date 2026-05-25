package com.mutissx.rickmortykmpclaude.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    @SerialName("info") val info: InfoDto,
    @SerialName("results") val results: List<CharacterDto>
)

@Serializable
data class InfoDto(
    @SerialName("count") val count: Int,
    @SerialName("pages") val pages: Int,
    @SerialName("next") val next: String?,
    @SerialName("prev") val prev: String?
)
