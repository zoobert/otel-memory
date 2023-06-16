package com.example

import com.example.client.GeographyClient
import com.example.client.GeographyClientImpl
import com.typesafe.config.ConfigFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.net.URL
import java.util.Properties

val modules = module {
    single {
        Properties().apply {
            ConfigFactory.load().entrySet().forEach {
                setProperty(it.key, it.value.unwrapped().toString())
            }
        }
    }

    single {

        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        coerceInputValues = true
                    },
                )
            }
            install(Auth) {
                val accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
                bearer {
                    loadTokens {
                        BearerTokens(accessToken, accessToken)
                    }
                    refreshTokens {
                        BearerTokens(accessToken, accessToken)
                    }
                }
            }
            expectSuccess = true
            engine {
            }
        }
    }

    single<GeographyClient> { GeographyClientImpl(get(), get()) }
}
