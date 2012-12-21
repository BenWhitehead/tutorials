package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ben Whitehead
 */
public interface StateDao extends Dao<Province, Long> {

    @NotNull
    List<Province> findAll();

}
