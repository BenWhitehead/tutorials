package com.github.benwhitehead.tutorials.spring.jersey.dao;

import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ben Whitehead
 */
public interface ProductDao {

    @Nullable
    Product findById(long id);

    @NotNull
    List<Product> findAll();

    Product findBySku(@NotNull String sku);
}
