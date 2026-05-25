package com.mutissx.rickmortykmpclaude.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutissx.rickmortykmpclaude.domain.model.Character
import com.mutissx.rickmortykmpclaude.domain.model.Episode
import com.mutissx.rickmortykmpclaude.domain.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface CharacterDetailState {
    data object Loading : CharacterDetailState
    data class Success(val character: Character, val episodes: List<Episode>) : CharacterDetailState
    data class Error(val message: String) : CharacterDetailState
}

class CharacterDetailViewModel(
    private val useCase: GetCharacterDetailUseCase,
    private val characterId: Int
) : ViewModel() {

    private val _state = MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val state: StateFlow<CharacterDetailState> = _state

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        viewModelScope.launch {
            _state.value = CharacterDetailState.Loading
            useCase.getCharacter(characterId)
                .onSuccess { character ->
                    val episodesResult = useCase.getEpisodes(character.episodeUrls.take(5))
                    _state.value = CharacterDetailState.Success(
                        character = character,
                        episodes = episodesResult.getOrDefault(emptyList())
                    )
                }
                .onFailure { e ->
                    _state.value = CharacterDetailState.Error(e.message ?: "Unknown error")
                }
        }
    }
}
