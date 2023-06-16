@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("application")
    id("org.jetbrains.kotlin.jvm")

    alias(libs.plugins.springframework.springboot) apply true
    alias(libs.plugins.jetbrains.kotlin.spring) apply true
    alias(libs.plugins.jetbrains.kotlin.serialization) apply true
}

dependencies {
    platform("org.jetbrains.kotlin:kotlin-bom")
    implementation(enforcedPlatform(libs.springboot.bom))

    implementation(project(":model"))

    implementation(libs.springboot.starter.actuator)
    implementation(libs.springboot.starter.data.r2dbc)
    implementation(libs.springboot.starter.security)
    implementation(libs.springboot.starter.oauth2.resource.server)
    implementation(libs.springboot.starter.webflux)
    implementation(libs.springframework.experimental.micrometer.r2dbc)

    implementation(libs.jetbrains.kotlin.reflect)
    implementation(libs.jetbrains.kotlin.stdlib.jdk8)
    implementation(libs.jetbrains.kotlinx.coroutines.reactor)
    implementation(libs.jetbrains.kotlinx.datetime.core)
    implementation(libs.jetbrains.kotlinx.serialization.json)

    implementation(libs.flyway.core)
    implementation(libs.postgresql.r2dbc.postgresql)
    implementation(libs.r2dbc.r2dbc.pool)
    implementation(libs.r2dbc.r2dbc.proxy)

    implementation(libs.micrometer.tracing.bridge.otel)

    implementation(libs.opentelemetry.exporter.otlp)
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
}

application {
    mainClass.set("com.example.GeographyServiceAppKt")
}
