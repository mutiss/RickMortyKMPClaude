package com.mutissx.rickmortykmpclaude.di

import org.koin.core.module.Module

fun appModules(): List<Module> = listOf(
    platformDatabaseModule(),
    databaseModule,
    networkModule,
    dataModule,
    domainModule,
    presentationModule
)
