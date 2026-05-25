package com.mutissx.rickmortykmpclaude.di

import com.mutissx.rickmortykmpclaude.presentation.viewmodel.CharacterDetailViewModel
import com.mutissx.rickmortykmpclaude.presentation.viewmodel.CharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { (id: Int) -> CharacterDetailViewModel(get(), id) }
}
