package com.mutissx.rickmortykmpclaude

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandlerEffect(enabled: Boolean, onBack: () -> Unit)
