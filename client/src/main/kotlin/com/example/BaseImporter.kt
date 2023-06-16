package com.example

import com.example.client.GeographyClient
import com.example.model.CreateGeographyRequest
import com.example.model.GeographyClassification
import com.example.model.GeographyDTO
import mu.KLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseImporter(protected val logger: KLogger) : KoinComponent {

    protected val geographyClient: GeographyClient by inject()

    suspend fun getOrCreateGeography(
        parentGeographyId: String,
        name: String,
        isoCode: String?,
    ): GeographyDTO =
        try {
            logger.debug { "Getting geography $name..." }
            val geographyDTO = geographyClient.getByParentIdAndName(
                parentId = parentGeographyId,
                name = name,
            )

            when (geographyDTO) {
                null -> {
                    logger.debug { "Geography $name not found, creating..." }
                    geographyClient.create(
                        CreateGeographyRequest(
                            parentId = parentGeographyId,
                            name = name,
                            isoCode = isoCode,
                        ),
                    )
                }

                else -> {
                    geographyDTO
                }
            }
        } catch (exception: Exception) {
            logger.error(exception) { "Error getting/creating geography $name" }
            throw exception
        }
}
