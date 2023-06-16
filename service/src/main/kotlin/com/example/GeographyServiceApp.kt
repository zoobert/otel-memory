package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication // (exclude = [OpenTelemetryLoggingConfig::class])
class GeographyServiceApp

fun main(args: Array<String>) {
    Hooks.enableAutomaticContextPropagation()

    runApplication<GeographyServiceApp>(*args)
}
