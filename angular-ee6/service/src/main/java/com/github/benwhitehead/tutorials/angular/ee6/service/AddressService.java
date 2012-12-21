package com.github.benwhitehead.tutorials.angular.ee6.service;

import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Ben Whitehead
 */
public interface AddressService {
    @NotNull
    List<Address> findAllAddresses();

    @Nullable
    Address findAddress(@NotNull Long id);

    @NotNull
    Address persist(@NotNull Address entity);

    @NotNull
    List<Province> findAllStates();

    @NotNull
    List<Country> findAllCountries();
}
