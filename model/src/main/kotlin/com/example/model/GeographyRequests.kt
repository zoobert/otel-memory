package com.example.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CreateGeographyRequest(
    val parentId: String,
    val name: String,
    val isoCode: String? = null
)
