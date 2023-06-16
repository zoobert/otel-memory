package com.example.dataaccess

import com.example.service.IdGenerator
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Component

@Table("ts_geography")
data class Geography(
    @Id val id: String,
    @Column val parentId: String,
    @Column val name: String,
    @Column val isoCode: String?,
    @Transient val createNew: Boolean = false,
) : PersistableEntity<String>(id, createNew) {
    @PersistenceCreator
    constructor(
        id: String,
        parentId: String,
        name: String,
        isoCode: String?,
    ) : this(
        id = id,
        parentId = parentId,
        name = name,
        isoCode = isoCode,
        createNew = false,
    )
}

@Component
class GeographyBuilder(private val idGenerator: IdGenerator) {
    fun build(
        parentId: String,
        name: String,
        isoCode: String?,
    ) = Geography(
        id = idGenerator.getNextId(),
        parentId = parentId,
        name = name,
        isoCode = isoCode,
        createNew = true,
    )
}
