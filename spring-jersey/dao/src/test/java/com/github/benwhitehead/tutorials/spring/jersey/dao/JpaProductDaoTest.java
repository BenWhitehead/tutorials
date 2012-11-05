package com.github.benwhitehead.tutorials.spring.jersey.dao;

import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Ben Whitehead
 */
@RunWith(JMock.class)
public class JpaProductDaoTest {

    private Mockery mockery;

    private EntityManager entityManager;

    private JpaProductDao productDao;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();

        productDao = new JpaProductDao();

        entityManager = mockery.mock(EntityManager.class);
        productDao.setEm(entityManager);
    }

    @Test
    public void testFindById() throws Exception {
        final Product result = new Product();
        mockery.checking(new Expectations(){{
            one(entityManager).find(Product.class, 1L);
            will(returnValue(result));
        }});

        assertEquals(result, productDao.findById(1L));
    }

    @Test
    public void testFindById_notFound() throws Exception {
        mockery.checking(new Expectations(){{
            one(entityManager).find(Product.class, 1L);
            will(returnValue(null));
        }});

        assertNull(productDao.findById(1L));
    }

    @Test
    public void testFindAll() throws Exception {
        final TypedQuery<Product> mockQuery = mockery.mock(TypedQuery.class);
        mockery.checking(new Expectations() {{
            one(entityManager).createNamedQuery("product.find.all", Product.class);
            will(returnValue(mockQuery));
            one(mockQuery).getResultList();
            will(returnValue(Collections.emptyList()));
        }});

        assertTrue(productDao.findAll().isEmpty());
    }

    @Test
    public void testFindBySku() throws Exception {
        final TypedQuery<Product> mockQuery = mockery.mock(TypedQuery.class);
        final Product result = new Product();
        mockery.checking(new Expectations() {{
            one(entityManager).createNamedQuery("product.find.by.sku", Product.class);
            will(returnValue(mockQuery));
            one(mockQuery).setParameter("sku", "fakeSku");
            one(mockQuery).getSingleResult();
            will(returnValue(result));
        }});

        assertEquals(result, productDao.findBySku("fakeSku"));
    }

    @Test
    public void testFindBySku_notFound() throws Exception {
        final TypedQuery<Product> mockQuery = mockery.mock(TypedQuery.class);
        mockery.checking(new Expectations() {{
            one(entityManager).createNamedQuery("product.find.by.sku", Product.class);
            will(returnValue(mockQuery));
            one(mockQuery).setParameter("sku", "fakeSku");
            one(mockQuery).getSingleResult();
            will(throwException(new NoResultException()));
        }});

        assertNull(productDao.findBySku("fakeSku"));
    }
}
