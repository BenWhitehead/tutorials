package com.github.benwhitehead.tutorials.spring.jersey.dao.data;

import com.github.benwhitehead.tutorials.spring.jersey.model.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

/**
 * This is a fairly simple class used to populate the in memory database with some initial data
 * to facilitate showing how JAX-RS can externalize the objects over REST with very little effort
 *
 * @author Ben Whitehead
 */
@Transactional
class DataLoader {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @PersistenceContext
    private EntityManager em;

    // This needs to be static so that tests ran in the same vm will work ok
    private static boolean initialized = false;

    public void initData() {
        LOG.info("Inserting data to database");
        if (initialized) {
            LOG.warn("initData called again.");
            return;
        }

        final Category fruit = createCategory(1L, "Fruit");
        final Category dairy = createCategory(2L, "Dairy");

        createProduct(10L, "Apple", 0.59, fruit);
        createProduct(20L, "Milk", 2.99, dairy);

        initialized = true;
        LOG.info("Completed inserting data into database");
    }

    @NotNull
    private Category createCategory(@NotNull final Long id, @NotNull final String name) {
        final Category category = new Category();
        category.setId(Long.toString(id));
        category.setName(name);
        em.persist(category);
        return category;
    }

    @NotNull
    private Product createProduct(
            @NotNull Long id,
            @NotNull final String name,
            final double price,
            final Category... categoriesToAddTo
    ) {
        final Product product = new Product();
        product.setId(id);
        final String sku = "product-" + id;
        product.setSku(sku);
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        if (categoriesToAddTo.length > 0) {
            for (final Category category : categoriesToAddTo) {
                product.getCategories().add(category);
            }
        }
        em.persist(product);
        return product;
    }

}
