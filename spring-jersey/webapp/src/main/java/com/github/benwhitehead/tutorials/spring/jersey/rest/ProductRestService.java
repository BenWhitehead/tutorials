package com.github.benwhitehead.tutorials.spring.jersey.rest;

import com.github.benwhitehead.tutorials.spring.jersey.model.Product;
import com.github.benwhitehead.tutorials.spring.jersey.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import java.util.List;

/**
 * @author Ben Whitehead
 */
@Path("/product")
@Produces("application/json")
@Consumes("application/json")
@Transactional
public class ProductRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestService.class);

    @NotNull
    private ProductService productService;

    @GET
    @NotNull
    public List<Product> findAll() {
        LOGGER.debug("findAll()");
        return productService.findAll();
    }

    /**
     * Restful Webservice Method that returns a product base on the id
     *
     * @param id String representing the product Id
     *
     * @return {@link com.github.benwhitehead.tutorials.spring.jersey.model.Product}
     */
    @GET
    @Path("/{id}")
    public Product findProductById(@PathParam("id") final long id) {
        LOGGER.debug("findProductById(id : {})", id);
        return productService.findById(id);
    }

    @GET
    @Path("/sku/{sku}")
    public Product findProductBySku(@PathParam("sku") @NotNull final String sku) {
        LOGGER.debug("findProductBySku(sku : {})", sku);
        return productService.findBySku(sku);
    }

    public void setProductService(@NotNull final ProductService productService) {
        this.productService = productService;
    }
}
