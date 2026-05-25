package com.mutissx.rickmortykmpclaude.domain.repository

import androidx.paging.PagingData
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersPagingData(query: String = ""): Flow<PagingData<Character>>
    suspend fun getCharacter(id: Int): Result<Character>
    suspend fun getEpisodes(urls: List<String>): Result<List<Episode>>
}
