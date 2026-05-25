package com.mutissx.rickmortykmpclaude.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mutissx.rickmortykmpclaude.db.CharacterDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseModule(): Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = CharacterDatabase.Schema,
            context = get<Context>(),
            name = "character_v2.db"
        )
    }
}
