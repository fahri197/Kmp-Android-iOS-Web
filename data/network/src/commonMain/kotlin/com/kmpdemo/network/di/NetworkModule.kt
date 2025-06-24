package com.kmpdemo.network.di

import com.kmpdemo.network.api.HomeApiService
import com.kmpdemo.network.api.HomeApiServiceImpl
import com.kmpdemo.network.api.MatchApiService
import com.kmpdemo.network.api.MatchApiServiceImpl
import com.kmpdemo.network.getPlatformHttpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import org.koin.core.qualifier.named
import org.koin.dsl.module
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

val networkModule = module {

    single<HttpClientEngine> { getPlatformHttpClientEngine() }

    single<HttpClient> {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                accept(ContentType.Application.Json)
                url(get<String>(named("base_url")))
            }
        }
    }

    single<MatchApiService> {
        MatchApiServiceImpl(get())
    }

    single<HomeApiService> {
        HomeApiServiceImpl(get())
    }
}