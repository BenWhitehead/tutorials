package com.github.benwhitehead.tutorials.spring.jersey.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Ben Whitehead
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "category.find.all", query = "select c from Category c"),
    @NamedQuery(name = "category.find.by.name", query = "select c from Category c where lower(c.name) = lower(:name)")
})
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")
    @NotNull
    private String id;

    @Column(name = "name")
    @NotNull
    private String name;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    private Collection<Product> products;

    public Category() {
        name = "";
        products = new ArrayList<Product>();
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull final String id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    @XmlTransient
    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(@NotNull final Collection<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }

        final Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) {
            return false;
        }
        if (!name.equals(category.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Category");
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
