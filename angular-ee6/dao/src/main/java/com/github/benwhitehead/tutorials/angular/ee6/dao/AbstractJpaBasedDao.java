package com.github.benwhitehead.tutorials.angular.ee6.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * @author Ben Whitehead
 */
abstract class AbstractJpaBasedDao<EntityType, EntityIdType extends Serializable & Comparable<EntityIdType>> implements Dao<EntityType, EntityIdType> {

    @NotNull
    @PersistenceContext
    protected EntityManager em;

    @NotNull
    private final Class<EntityType> entityTypeClass;

    protected AbstractJpaBasedDao(@NotNull final Class<EntityType> entityTypeClass) {
        this.entityTypeClass = entityTypeClass;
    }

    @Nullable
    @Override
    public EntityType find(@NotNull final EntityIdType id) {
        return em.find(entityTypeClass, id);
    }

    @NotNull
    @Override
    public EntityType merge(@NotNull final EntityType entity) {
        return em.merge(entity);
    }

    @NotNull
    @Override
    public EntityType refresh(@NotNull final EntityType entity) {
        em.refresh(entity);
        return entity;
    }

    @NotNull
    @Override
    public EntityType persist(@NotNull final EntityType entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(@NotNull final EntityType entity) {
        em.remove(entity);
    }

    @Nullable
    protected final EntityType getSingleResultFromQuery(@NotNull final TypedQuery<EntityType> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}
