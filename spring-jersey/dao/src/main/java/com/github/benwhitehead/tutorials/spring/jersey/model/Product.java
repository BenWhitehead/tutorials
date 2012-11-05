package com.github.benwhitehead.tutorials.spring.jersey.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Ben Whitehead
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "product.find.all", query = "select p from Product p"),
    @NamedQuery(name = "product.find.by.sku", query = "select p from Product p where p.sku = :sku")
})
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "sku")
    @NotNull
    private String sku;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name="price", precision = 19, scale = 2)
    private BigDecimal price;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="product_x_category",
               joinColumns={@JoinColumn(name="product_id")},
               inverseJoinColumns={@JoinColumn(name="category_id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Category> categories;

    public Product() {
        sku = "";
        name = "";
        categories = new ArrayList<Category>();
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull final Long id) {
        this.id = id;
    }

    @NotNull
    public String getSku() {
        return sku;
    }

    public void setSku(@NotNull final String sku) {
        this.sku = sku;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    @NotNull
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(@NotNull final Collection<Category> categories) {
        this.categories = categories;
    }
}
