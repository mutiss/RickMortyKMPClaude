package com.mutissx.rickmortykmpclaude.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mutissx.rickmortykmpclaude.db.CharacterDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseModule(): Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            schema = CharacterDatabase.Schema,
            name = "character_v2.db"
        )
    }
}
