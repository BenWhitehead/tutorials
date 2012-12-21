package com.github.benwhitehead.tutorials.angular.ee6.rest;

import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import com.github.benwhitehead.tutorials.angular.ee6.service.AddressService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Ben Whitehead
 */
@RunWith(JMock.class)
public class AddressRestServiceTest {

    private Mockery mockery;

    private AddressService addressService;

    private AddressRestService addressRestService;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        addressService = mockery.mock(AddressService.class);
        addressRestService = new AddressRestService();
        addressRestService.addressService = addressService;
    }

    @Test
    public void testFindAllCountries() throws Exception {
        final List<Country> countries = Collections.emptyList();
        mockery.checking(new Expectations(){{
            one(addressService).findAllCountries();
            will(returnValue(countries));
        }});

        final List<Country> all = addressRestService.findAllCountries();
        assertNotNull(all.isEmpty());
        assertEquals(countries, all);
    }

    @Test
    public void testFindAllStates() throws Exception {
        final List<Province> provinces = Collections.emptyList();
        mockery.checking(new Expectations(){{
            one(addressService).findAllStates();
            will(returnValue(provinces));
        }});

        final List<Province> all = addressRestService.findAllStates();
        assertNotNull(all.isEmpty());
        assertEquals(provinces, all);
    }

    @Test
    public void testFindAll() throws Exception {
        final List<Address> addresses = Collections.emptyList();
        mockery.checking(new Expectations(){{
            one(addressService).findAllAddresses();
            will(returnValue(addresses));
        }});

        final List<Address> all = addressRestService.index();
        assertNotNull(all.isEmpty());
        assertEquals(addresses, all);
    }

    @Test
    public void testCreateAddress() throws Exception {
        final Address address = new Address();
        mockery.checking(new Expectations() {{
            one(addressService).persist(address);
            will(returnValue(address));
        }});

        final Address persistedAddress = addressRestService.createAddress(address);
        assertNotNull(persistedAddress);
        assertEquals(address, persistedAddress);
    }
}
