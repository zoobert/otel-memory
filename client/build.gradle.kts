@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("application")
    id("org.jetbrains.kotlin.jvm")

    alias(libs.plugins.jetbrains.kotlin.serialization) apply true
}

dependencies {
    platform("org.jetbrains.kotlin:kotlin-bom")
    implementation(project(":model"))

    implementation(libs.jetbrains.kotlin.reflect)
    implementation(libs.jetbrains.kotlin.stdlib.jdk8)
//    implementation(libs.jetbrains.kotlinx.cli)
    implementation(libs.jetbrains.kotlinx.datetime.core)
    implementation(libs.jetbrains.kotlinx.serialization.json)

    implementation(libs.ktor.client.auth.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.koin.core)
    implementation(libs.koin.ktor)

    // Spreadsheet reading, content files
    implementation(libs.apache.poi.core)
    implementation(libs.apache.poi.ooxml)
    implementation("dev.reformator.stacktracedecoroutinator:stacktrace-decoroutinator-jvm:2.3.6")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation("net.logstash.logback:logstash-logback-encoder:7.3")
    implementation("org.slf4j:slf4j-api:2.0.7")
}

task("runGeographyImporter", JavaExec::class) {
    mainClass.set("com.example.GeographyImporterKt")

    classpath = sourceSets["main"].runtimeClasspath
}

task("runRandomImporter", JavaExec::class) {
    mainClass.set("com.example.RandomImporterKt")

    classpath = sourceSets["main"].runtimeClasspath
}