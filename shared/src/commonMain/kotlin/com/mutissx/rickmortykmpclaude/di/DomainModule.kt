package com.mutissx.rickmortykmpclaude.di

import com.mutissx.rickmortykmpclaude.domain.usecase.GetCharacterDetailUseCase
import com.mutissx.rickmortykmpclaude.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }
}
