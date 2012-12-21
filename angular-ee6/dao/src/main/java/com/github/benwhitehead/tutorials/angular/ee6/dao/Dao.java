package com.github.benwhitehead.tutorials.angular.ee6.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * @author Ben Whitehead
 */
public interface Dao<EntityType, EntityIdType extends Serializable & Comparable<EntityIdType>> {

    @Nullable
    EntityType find(@NotNull final EntityIdType id);

    @NotNull
    EntityType merge(@NotNull final EntityType entity);

    @NotNull
    EntityType refresh(@NotNull final EntityType entity);

    @NotNull
    EntityType persist(@NotNull final EntityType entity);

    void delete(@NotNull final EntityType entity);

}
