package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Ben Whitehead
 */
class AddressDaoImpl extends AbstractJpaBasedDao<Address,Long> implements AddressDao {

    public AddressDaoImpl() {
        super(Address.class);
    }

    @NotNull
    @Override
    public List<Address> findAll() {
        return em.createNamedQuery("address.find.all", Address.class).getResultList();
    }
}
