package com.mutissx.rickmortykmpclaude.data.remote

import com.mutissx.rickmortykmpclaude.BASE_URL
import com.mutissx.rickmortykmpclaude.data.remote.dto.CharacterDto
import com.mutissx.rickmortykmpclaude.data.remote.dto.CharacterResponseDto
import com.mutissx.rickmortykmpclaude.data.remote.dto.EpisodeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

class CharacterApiService(
    private val httpClient: HttpClient,
    private val json: Json
) {

    suspend fun getCharacters(page: Int, query: String = ""): CharacterResponseDto =
        httpClient.get("$BASE_URL/character") {
            parameter("page", page)
            if (query.isNotBlank()) parameter("name", query)
        }.body()

    suspend fun getCharacter(id: Int): CharacterDto =
        httpClient.get("$BASE_URL/character/$id").body()

    suspend fun getEpisodes(ids: List<Int>): List<EpisodeDto> {
        if (ids.isEmpty()) return emptyList()
        if (ids.size == 1) {
            return listOf(httpClient.get("$BASE_URL/episode/${ids.first()}").body())
        }
        val element = httpClient.get("$BASE_URL/episode/${ids.joinToString(",")}").body<kotlinx.serialization.json.JsonElement>()
        return when (element) {
            is JsonArray -> json.decodeFromJsonElement(element)
            is JsonObject -> listOf(json.decodeFromJsonElement(element))
            else -> emptyList()
        }
    }
}
