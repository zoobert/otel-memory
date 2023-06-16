package com.example.client

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend fun clientCall(block: suspend () -> HttpResponse): HttpResponse? {
    return try {
        block()
    } catch (exception: ClientRequestException) {
        when (exception.response.status) {
            HttpStatusCode.NotFound -> null
            else -> throw exception
        }
    } catch (exception: Exception) {
        throw exception
    }
}
