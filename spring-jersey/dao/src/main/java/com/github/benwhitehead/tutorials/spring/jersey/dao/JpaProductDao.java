package com.github.benwhitehead.tutorials.spring.jersey.dao;

import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Ben Whitehead
 */
class JpaProductDao implements ProductDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaProductDao.class);

    @PersistenceContext
    private EntityManager em;

    @Nullable
    @Override
    public Product findById(final long id) {
        LOGGER.debug("findById(id : {})", id);
        return em.find(Product.class, id);
    }

    @NotNull
    @Override
    public List<Product> findAll() {
        LOGGER.debug("findAll()");
        return em.createNamedQuery("product.find.all", Product.class).getResultList();
    }

    @Override
    public Product findBySku(@NotNull final String sku) {
        LOGGER.debug("findBySku(sku : {})", sku);
        final TypedQuery<Product> namedQuery = em.createNamedQuery("product.find.by.sku", Product.class);
        namedQuery.setParameter("sku", sku);
        final Product singleResult;
        try {
            singleResult = namedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    void setEm(final EntityManager em) {
        this.em = em;
    }
}
