package com.mutissx.rickmortykmpclaude

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mutissx.rickmortykmpclaude.presentation.ui.CharacterDetailScreen
import com.mutissx.rickmortykmpclaude.presentation.ui.CharacterListScreen
import com.mutissx.rickmortykmpclaude.presentation.ui.theme.AppTheme

private data class SelectedCharacter(val id: Int, val name: String)

@Composable
fun App() {
    AppTheme {
        var selected by remember { mutableStateOf<SelectedCharacter?>(null) }

        BackHandlerEffect(enabled = selected != null) {
            selected = null
        }

        val current = selected

        Box(modifier = Modifier.fillMaxSize()) {
            CharacterListScreen(
                onCharacterClick = { id, name -> selected = SelectedCharacter(id, name) }
            )
            if (current != null) {
                CharacterDetailScreen(
                    characterId = current.id,
                    characterName = current.name,
                    onBack = { selected = null }
                )
            }
        }
    }
}
