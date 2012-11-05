package com.github.benwhitehead.tutorials.spring.jersey.service;

import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ben Whitehead
 */
public interface ProductService {

    @Nullable
    Product findById(long id);

    @Nullable
    Product findBySku(@NotNull String sku);

    @NotNull
    List<Product> findAll();
}
