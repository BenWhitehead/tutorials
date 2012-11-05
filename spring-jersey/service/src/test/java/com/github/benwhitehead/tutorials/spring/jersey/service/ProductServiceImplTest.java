package com.github.benwhitehead.tutorials.spring.jersey.service;

import com.github.benwhitehead.tutorials.spring.jersey.dao.ProductDao;
import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Ben Whitehead
 */
@RunWith(JMock.class)
public class ProductServiceImplTest {

    private Mockery mockery;

    private ProductDao productDao;
    private ProductServiceImpl productService;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();

        productDao = mockery.mock(ProductDao.class);

        productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
    }

    @Test
    public void testFindById() throws Exception {
        final Product product = new Product();
        mockery.checking(new Expectations(){{
            one(productDao).findById(1L);
            will(returnValue(product));
        }});

        assertEquals(product, productService.findById(1L));
    }

    @Test
    public void testFindBySku() throws Exception {
        final Product product = new Product();
        mockery.checking(new Expectations(){{
            one(productDao).findBySku("searchSku");
            will(returnValue(product));
        }});

        assertEquals(product, productService.findBySku("searchSku"));
    }

    @Test
    public void testFindAll() throws Exception {
        mockery.checking(new Expectations(){{
            one(productDao).findAll();
            will(returnValue(Collections.<Product>emptyList()));
        }});

        final List<Product> all = productService.findAll();
        assertTrue(all.isEmpty());
    }
}
