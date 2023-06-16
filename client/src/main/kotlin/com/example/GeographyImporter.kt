package com.example

import dev.reformator.stacktracedecoroutinator.runtime.DecoroutinatorRuntime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import java.io.File

fun main() {
    DecoroutinatorRuntime.load()

    startKoin {
        // declare modules
        modules(modules)
    }

    runBlocking { GeographyImporter().run() }
}

class GeographyImporter : BaseImporter(KotlinLogging.logger { }), KoinComponent {

    val filename = "/FIPS-Counties.txt"
    suspend fun getCountyData(): List<Pair<String, String>> =
        withContext(Dispatchers.IO) {
            this.javaClass.getResource(filename)?.let { url ->
                File(url.toURI())
                    .readLines()
                    .mapNotNull { line ->
                        line
                            .trim()
                            .let { trimmedLine ->
                                val trimmedName = trimmedLine.substring(5).trim()
                                val isCity = trimmedName.endsWith(suffix = "city", ignoreCase = true)
                                if (isCity) {
                                    // ignore anything in the file that ends with "city"
                                    null
                                } else {
                                    // find the name - will end with one of these not have a suffix
                                    // it is possible that there is no suffix, if so use the complete name
                                    val countyName = listOf("borough", "county", "parish")
                                        .map { trimmedName.indexOf(string = it, ignoreCase = true) }
                                        .filter { it != -1 }
                                        .firstOrNull()
                                        ?.let { trimmedName.substring(startIndex = 0, endIndex = it).trim() }
                                        ?: trimmedName

                                    Pair(
                                        trimmedLine.take(5),
                                        countyName.uppercase(),
                                    )
                                }
                            }
                    }
            } ?: throw IllegalArgumentException("File not found: $filename")
        }

    fun getStateIsoCode(name: String): String =
        USGeography.US_STATES.first { it.name == name }.isoCode
            ?: throw IllegalArgumentException("Invalid US State name: $name")

    suspend fun importCountyFIPS() {
        logger.info { "Processing counties..." }
        val pairs = getCountyData()
        withContext(Dispatchers.IO) {
            val unitedStatedId = "000"

            var stateId = "0"
            pairs.forEach { (numericCode, name) ->
                val isState = numericCode.substring(2) == "000"
                when (isState) {
                    true -> {
                        stateId = getOrCreateGeography(
                            parentGeographyId = unitedStatedId,
                            name = name,
                            isoCode = getStateIsoCode(name),
                        ).id
                    }

                    false -> {
                        getOrCreateGeography(
                            parentGeographyId = stateId,
                            name = name,
                            isoCode = null,
                        )
                    }
                }
            }
        }

        logger.info { "Processing of counties complete" }
    }

    suspend fun run() {
        importCountyFIPS()
    }
}
