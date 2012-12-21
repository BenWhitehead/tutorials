package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Ben Whitehead
 */
class StateDaoImpl extends AbstractJpaBasedDao<Province, Long> implements StateDao {

    public StateDaoImpl() {
        super(Province.class);
    }

    @NotNull
    @Override
    public List<Province> findAll() {
        return em.createNamedQuery("province.find.all", Province.class).getResultList();
    }

}

