package com.mutissx.rickmortykmpclaude

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun ShowCharacterToastEffect(characterName: String) {
    val context = LocalContext.current
    LaunchedEffect(characterName) {
        Toast.makeText(context, characterName, Toast.LENGTH_SHORT).show()
    }
}
