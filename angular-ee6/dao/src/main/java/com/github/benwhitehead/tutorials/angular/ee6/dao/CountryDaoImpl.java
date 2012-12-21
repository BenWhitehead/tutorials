package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Ben Whitehead
 */
class CountryDaoImpl extends AbstractJpaBasedDao<Country, Long> implements CountryDao {

    public CountryDaoImpl() {
        super(Country.class);
    }

    @NotNull
    @Override
    public List<Country> findAll() {
        return em.createNamedQuery("country.find.all", Country.class).getResultList();
    }

}
