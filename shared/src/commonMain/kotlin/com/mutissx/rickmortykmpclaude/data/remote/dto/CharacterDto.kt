package com.mutissx.rickmortykmpclaude.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("status") val status: String,
    @SerialName("species") val species: String,
    @SerialName("gender") val gender: String,
    @SerialName("origin") val origin: LocationDto,
    @SerialName("location") val location: LocationDto,
    @SerialName("episode") val episode: List<String>
)
