package com.mutissx.rickmortykmpclaude.data.mapper

import com.mutissx.rickmortykmpclaude.data.remote.dto.CharacterDto
import com.mutissx.rickmortykmpclaude.data.remote.dto.EpisodeDto
import com.mutissx.rickmortykmpclaude.db.CharacterEntity
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.model.Episode

fun CharacterDto.toDomain(): Character = Character(
    id = id,
    name = name,
    imageUrl = image,
    status = status,
    species = species,
    gender = gender,
    originName = origin.name,
    locationName = location.name,
    episodeUrls = episode
)

fun CharacterDto.toEntity(page: Int): CharacterEntity = CharacterEntity(
    id = id.toLong(),
    name = name,
    imageUrl = image,
    status = status,
    species = species,
    gender = gender,
    originName = origin.name,
    locationName = location.name,
    episodeUrls = episode.joinToString("|"),
    page = page.toLong()
)

fun CharacterEntity.toDomain(): Character = Character(
    id = id.toInt(),
    name = name,
    imageUrl = imageUrl,
    status = status,
    species = species,
    gender = gender,
    originName = originName,
    locationName = locationName,
    episodeUrls = episodeUrls.split("|").filter { it.isNotEmpty() }
)

fun EpisodeDto.toDomain(): Episode = Episode(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode
)
