package com.mutissx.rickmortykmpclaude.domain.usecase

import androidx.paging.PagingData
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(private val repository: CharacterRepository) {
    operator fun invoke(query: String = ""): Flow<PagingData<Character>> =
        repository.getCharactersPagingData(query)
}
