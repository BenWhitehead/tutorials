package com.github.benwhitehead.tutorials.spring.jersey.rest;

import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import com.github.benwhitehead.tutorials.spring.jersey.service.ProductService;
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
public class ProductRestServiceTest {

    private Mockery mockery;
    private ProductService productService;
    private ProductRestService productRestService;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        productService = mockery.mock(ProductService.class);
        productRestService = new ProductRestService();
        productRestService.setProductService(productService);
    }

    @Test
    public void testFindAll() throws Exception {
        mockery.checking(new Expectations(){{
            one(productService).findAll();
            will(returnValue(Collections.<Product>emptyList()));
        }});
        final List<Product> all = productRestService.findAll();
        assertTrue(all.isEmpty());
    }

    @Test
    public void testFindProductById() throws Exception {
        final Product result = new Product();
        mockery.checking(new Expectations(){{
            one(productService).findById(1L);
            will(returnValue(result));
        }});
        assertEquals(result, productRestService.findProductById(1l));
    }

    @Test
    public void testFindProductBySku() throws Exception {
        final Product result = new Product();
        mockery.checking(new Expectations(){{
            one(productService).findBySku("fakeSku");
            will(returnValue(result));
        }});
        assertEquals(result, productRestService.findProductBySku("fakeSku"));
     }

}
