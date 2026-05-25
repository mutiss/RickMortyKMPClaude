package com.mutissx.rickmortykmpclaude.domain.usecase

import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.model.Episode
import com.mutissx.rickmortykmpclaude.domain.repository.CharacterRepository

class GetCharacterDetailUseCase(private val repository: CharacterRepository) {
    suspend fun getCharacter(id: Int): Result<Character> = repository.getCharacter(id)
    suspend fun getEpisodes(urls: List<String>): Result<List<Episode>> = repository.getEpisodes(urls)
}
