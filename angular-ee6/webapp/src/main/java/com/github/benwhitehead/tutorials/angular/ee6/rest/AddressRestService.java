package com.github.benwhitehead.tutorials.angular.ee6.rest;

import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import com.github.benwhitehead.tutorials.angular.ee6.service.AddressService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * @author Ben Whitehead
 */
@Path("/address")
@Produces({"application/xml", "application/json"})
@Stateless
public class AddressRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressRestService.class);

    @NotNull
    @Inject
    AddressService addressService;

    @GET
    @Path("/country")
    @NotNull
    public List<Country> findAllCountries() {
        LOGGER.info("findAllCountries()");
        return addressService.findAllCountries();
    }

    @GET
    @Path("/state")
    @NotNull
    public List<Province> findAllStates() {
        LOGGER.info("findAllStates()");
        return addressService.findAllStates();
    }

    @GET
    @Path("/")
    @NotNull
    public List<Address> index() {
        LOGGER.info("index()");
        return addressService.findAllAddresses();
    }

    @POST
    @NotNull
    public Address createAddress(@NotNull final Address data) {
        LOGGER.info("createAddress(data : {})", data);
        final Address persist = addressService.persist(data);
        LOGGER.info("returning persisted address: {}", persist);
        return persist;
    }
}
