package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ben Whitehead
 */
public interface CountryDao extends Dao<Country, Long> {

    @NotNull
    List<Country> findAll();

}
