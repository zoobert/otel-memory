package com.example.dataaccess

import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable

/**
 * Base class for persisted entities.  This is required to support manual ID generation.
 * In Spring Data CrudRepository land a null ID value means 'create new'.  In our case
 * the ID will always be application/manually created.  This class allows for Spring to identify
 * that a new entity is being created.
 *
 * Note: each class will also need to implement a constructor annotated with @PersistenceConstructor
 * that is used for persisted data creation; Spring will call this constructor when doing reads.
 */
open class PersistableEntity<ID : Any>(@Transient val entityId: ID, @Transient val createEntity: Boolean) : Persistable<ID> {
    /**
     * Returns the id of the entity.
     *
     * @return the id. Can be null.
     */
    override fun getId(): ID {
        return entityId
    }

    /**
     * Returns if the `Persistable` is new or was persisted already.
     *
     * @return if true the object is new.
     */
    override fun isNew(): Boolean {
        return createEntity
    }
}
