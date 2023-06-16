package com.example.client

import com.example.model.CreateGeographyRequest
import com.example.model.GeographyDTO

interface GeographyClient {
    suspend fun create(request: CreateGeographyRequest): GeographyDTO

    suspend fun getById(id: String): GeographyDTO?

    suspend fun getAllByName(name: String): List<GeographyDTO>

    suspend fun getByParentIdAndName(
        parentId: String,
        name: String
    ): GeographyDTO?

    suspend fun getByParentId(parentId: String): List<GeographyDTO>
}
