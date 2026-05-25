package com.mutissx.rickmortykmpclaude.domain.model

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String,
    val species: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val episodeUrls: List<String>
)
