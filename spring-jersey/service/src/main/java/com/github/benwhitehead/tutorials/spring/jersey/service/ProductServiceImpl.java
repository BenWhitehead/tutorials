package com.github.benwhitehead.tutorials.spring.jersey.service;

import com.github.benwhitehead.tutorials.spring.jersey.dao.ProductDao;
import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Ben Whitehead
 */
class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductDao productDao;

    @Override
    @Nullable
    public Product findById(final long id) {
        LOGGER.debug("findById(id : {})", id);
        return productDao.findById(id);
    }

    @Override
    @Nullable
    public Product findBySku(@NotNull final String sku) {
        LOGGER.debug("findBySku(sku : {})", sku);
        return productDao.findBySku(sku);
    }

    @Override
    @NotNull
    public List<Product> findAll() {
        LOGGER.debug("findAll()");
        return productDao.findAll();
    }

    public void setProductDao(final ProductDao productDao) {
        this.productDao = productDao;
    }
}
