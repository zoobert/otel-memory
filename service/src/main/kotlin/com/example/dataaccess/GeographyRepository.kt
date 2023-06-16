package com.example.dataaccess

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface GeographyRepository : CoroutineCrudRepository<Geography, String> {

    suspend fun findByName(name: String): Flow<Geography>

    suspend fun findByParentId(
        parentId: String,
    ): Flow<Geography>

    suspend fun findByParentIdAndName(
        parentId: String,
        name: String,
    ): Geography?
}
