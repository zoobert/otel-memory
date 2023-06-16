package com.example.model

import kotlinx.serialization.Serializable

/**
 * Represents geography globally
 */
@Serializable
data class GeographyDTO(
    val id: String,
    val parentId: String,
    val name: String,
    val isoCode: String? = null
) {
    companion object {
        const val WORLD_ID = "-1"
    }
}

@Serializable
enum class GeographyClassification(val id: Int) {
    UNKNOWN(-1),
    WORLD(0),
    ECONOMIC_UNION(1),
    COUNTRY(2),
    DIVISION(4),
    COUNTY(5),
    CITY(6),
    DISTRICT(7),
    POSTCODE(8),
    GEOCODE(9);

    companion object {
        private val idMap = GeographyClassification.values().associateBy(GeographyClassification::id)

        fun fromId(id: Int): GeographyClassification {
            val geographyClassification = idMap[id]

            return when (geographyClassification) {
                null -> UNKNOWN
                else -> geographyClassification
            }
        }
    }
}
