package com.example.service

import com.example.dataaccess.Geography
import com.example.dataaccess.GeographyBuilder
import com.example.dataaccess.GeographyRepository
import com.example.model.CreateGeographyRequest
import com.example.model.GeographyDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface GeographyService {
    suspend fun getById(id: String): GeographyDTO?

    suspend fun create(request: CreateGeographyRequest): GeographyDTO
    suspend fun getByParameter(
        parentId: String?,
        name: String?,
    ): Flow<GeographyDTO>
}

@Service
class GeographyServiceImpl(
    private val builder: GeographyBuilder,
    private val repository: GeographyRepository,
) : GeographyService {

    val logger = KotlinLogging.logger {}

    @Transactional
    override suspend fun create(request: CreateGeographyRequest): GeographyDTO =
        with(request) {
            builder.build(
                parentId = parentId,
                name = name,
                isoCode = isoCode,
            )
        }.let {
            repository.save(it).toDTO()
        }

    override suspend fun getById(id: String): GeographyDTO? =
        repository.findById(id)?.toDTO()

    override suspend fun getByParameter(
        parentId: String?,
        name: String?,
    ): Flow<GeographyDTO> = when {
        parentId != null -> {
            when {
                name != null -> {
                    flowOf(
                        repository.findByParentIdAndName(
                            parentId = parentId,
                            name = name,
                        )?.toDTO() ?: throw NotFoundException("Geography not found"),
                    )
                }

                else -> {
                    repository
                        .findByParentId(
                            parentId = parentId,
                        ).map { it.toDTO() }
                }
            }
        }

        name != null -> {
            repository
                .findByName(name = name)
                .map { it.toDTO() }
        }

        else -> throw BadRequestException("either name or parentId is required")
    }
}

fun Geography.toDTO(): GeographyDTO {
    return GeographyDTO(
        parentId = this.parentId,
        id = this.id,
        name = this.name,
        isoCode = this.isoCode,
    )
}
