package com.mutissx.rickmortykmpclaude

import androidx.compose.ui.window.ComposeUIViewController
import com.mutissx.rickmortykmpclaude.di.appModules
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(appModules())
    }
}