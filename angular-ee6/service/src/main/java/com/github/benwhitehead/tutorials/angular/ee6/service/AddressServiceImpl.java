package com.github.benwhitehead.tutorials.angular.ee6.service;

import com.github.benwhitehead.tutorials.angular.ee6.dao.AddressDao;
import com.github.benwhitehead.tutorials.angular.ee6.dao.CountryDao;
import com.github.benwhitehead.tutorials.angular.ee6.dao.StateDao;
import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ben Whitehead
 */
class AddressServiceImpl implements AddressService {

    @Inject
    @NotNull
    AddressDao addressDao;

    @Inject
    @NotNull
    StateDao stateDao;

    @Inject
    @NotNull
    CountryDao countryDao;

    @Override
    @NotNull
    public List<Address> findAllAddresses() {
        return addressDao.findAll();
    }

    @Override
    @Nullable
    public Address findAddress(@NotNull final Long id) {
        return addressDao.find(id);
    }

    @Override
    @NotNull
    public Address persist(@NotNull final Address entity) {
        return addressDao.persist(entity);
    }

    @Override
    @NotNull
    public List<Province> findAllStates() {
        return stateDao.findAll();
    }

    @Override
    @NotNull
    public List<Country> findAllCountries() {
        return countryDao.findAll();
    }
}
