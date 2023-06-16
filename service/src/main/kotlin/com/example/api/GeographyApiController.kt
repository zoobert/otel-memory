package com.example.api

import com.example.model.CreateGeographyRequest
import com.example.model.GeographyDTO
import com.example.service.GeographyService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@RestController
@RequestMapping("/v1/geography")
class GeographyApiController(private val geographyService: GeographyService) {
    @PostMapping
    suspend fun create(@RequestBody request: CreateGeographyRequest): GeographyDTO =
        geographyService.create(request)

    @GetMapping
    suspend fun getByParameter(
        @RequestParam parentId: String? = null,
        @RequestParam name: String? = null,
    ): Flow<GeographyDTO> =
        geographyService.getByParameter(
            parentId = parentId,
            name = when (name.isNullOrEmpty()) {
                true -> null
                false -> URLDecoder.decode(name, StandardCharsets.UTF_8.name())
            },
        )
}
