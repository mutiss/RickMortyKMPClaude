package com.mutissx.rickmortykmpclaude

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandlerEffect(enabled: Boolean, onBack: () -> Unit) {
    // iOS handles back navigation via the native swipe-to-go-back gesture
}
