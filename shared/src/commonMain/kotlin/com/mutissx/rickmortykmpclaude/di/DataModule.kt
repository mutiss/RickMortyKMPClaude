package com.mutissx.rickmortykmpclaude.di

import com.mutissx.rickmortykmpclaude.data.local.CharacterLocalDataSource
import com.mutissx.rickmortykmpclaude.data.repository.CharacterRepositoryImpl
import com.mutissx.rickmortykmpclaude.domain.repository.CharacterRepository
import org.koin.dsl.module

val dataModule = module {
    single { CharacterLocalDataSource(get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
}
