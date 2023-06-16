package com.example.client

import com.example.model.CreateGeographyRequest
import com.example.model.GeographyDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodeURLParameter
import java.util.Properties

class GeographyClientImpl(private val httpClient: HttpClient, properties: Properties) : GeographyClient {

    companion object {
        const val URL_PROPERTY_NAME = "example.geography-service.url"
    }

    private val serverAddress: String = properties.getProperty(URL_PROPERTY_NAME)
        ?: throw RuntimeException("$URL_PROPERTY_NAME was not set in configuration")

    private val baseUrl = "$serverAddress/v1/geography"

    override suspend fun create(request: CreateGeographyRequest): GeographyDTO {
        return httpClient.post(baseUrl) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun getById(id: String): GeographyDTO? =
        clientCall { httpClient.get("$baseUrl/$id") }?.body()

    override suspend fun getAllByName(name: String): List<GeographyDTO> {
        return httpClient.get(baseUrl) {
            parameter("name", name.encodeURLParameter())
        }.body()
    }

    override suspend fun getByParentIdAndName(
        parentId: String,
        name: String,
    ): GeographyDTO? =
        clientCall {
            httpClient.get(baseUrl) {
                parameter("parentId", parentId)
                parameter("name", name.encodeURLParameter())
            }
        }?.body<List<GeographyDTO>>()?.firstOrNull()

    override suspend fun getByParentId(
        parentId: String,
    ): List<GeographyDTO> {
        return httpClient.get(baseUrl) {
            parameter("parentId", parentId)
        }.body()
    }
}
