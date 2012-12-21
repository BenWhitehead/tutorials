package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Ben Whitehead
 */
public interface AddressDao extends Dao<Address, Long> {
    @NotNull
    List<Address> findAll();
}
