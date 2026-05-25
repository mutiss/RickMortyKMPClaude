package com.mutissx.rickmortykmpclaude.di

import com.mutissx.rickmortykmpclaude.data.remote.CharacterApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
    single {
        val appJson: Json = get()
        HttpClient {
            install(ContentNegotiation) { json(appJson) }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.BODY
            }
            install(HttpRequestRetry) {
                maxRetries = 3
                retryIf { _, response -> response.status.value == 429 }
                exponentialDelay(base = 2.0, maxDelayMs = 30_000L)
            }
            install(DefaultRequest) {
                headers.append(HttpHeaders.UserAgent, "RickAndMortyKMP/1.0 (Android; Kotlin)")
            }
        }
    }
    single { CharacterApiService(get(), get()) }
}
