package com.mutissx.rickmortykmpclaude.di

import app.cash.sqldelight.db.SqlDriver
import com.mutissx.rickmortykmpclaude.data.local.CharacterLocalDataSource
import com.mutissx.rickmortykmpclaude.db.CharacterDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformDatabaseModule(): Module

val databaseModule = module {
    single { CharacterDatabase(get<SqlDriver>()) }
    single { CharacterLocalDataSource(get()) }
}
