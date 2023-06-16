@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")

    alias(libs.plugins.jetbrains.kotlin.serialization) apply true
}

dependencies {
    platform("org.jetbrains.kotlin:kotlin-bom")

    implementation(libs.jetbrains.kotlin.reflect)
    implementation(libs.jetbrains.kotlin.stdlib.jdk8)
    implementation(libs.jetbrains.kotlinx.datetime.core)
    implementation(libs.jetbrains.kotlinx.serialization.json)
}
