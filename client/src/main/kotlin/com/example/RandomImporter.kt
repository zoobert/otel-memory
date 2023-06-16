package com.example

import com.example.USGeography.US_STATES
import dev.reformator.stacktracedecoroutinator.runtime.DecoroutinatorRuntime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.apache.commons.lang3.RandomStringUtils
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

fun main() {
    DecoroutinatorRuntime.load()

    startKoin {
        // declare modules
        modules(modules)
    }

    runBlocking { RandomImporter().run() }
}

class RandomImporter : BaseImporter(KotlinLogging.logger { }), KoinComponent {

    private val STRING_LENGTH = 30

    fun randomName() = RandomStringUtils.randomAlphanumeric(STRING_LENGTH)
    suspend fun importRandomCounties() {
        logger.info { "Importing counties..." }

        withContext(Dispatchers.IO) {
            US_STATES.forEach {
                val stateId = getOrCreateGeography(
                    parentGeographyId = "000",
                    name = it.name,
                    isoCode = it.isoCode,
                ).id

                logger.info { "Creating 1000 random counties for ${it.name}..." }
                repeat(1000) {
                    getOrCreateGeography(
                        parentGeographyId = stateId,
                        name = randomName(),
                        isoCode = null,
                    )
                }
            }
        }

        logger.info { "Processing of counties complete" }
    }

    suspend fun run() {
        importRandomCounties()
    }
}
