package com.github.benwhitehead.tutorials.angular.ee6.service;

import com.github.benwhitehead.tutorials.angular.ee6.dao.AddressDao;
import com.github.benwhitehead.tutorials.angular.ee6.dao.CountryDao;
import com.github.benwhitehead.tutorials.angular.ee6.dao.StateDao;
import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
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
public class AddressServiceImplTest {

    private Mockery mockery;

    private AddressDao addressDao;
    private StateDao stateDao;
    private CountryDao countryDao;
    
    private AddressServiceImpl addressService;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();

        addressDao = mockery.mock(AddressDao.class);
        stateDao = mockery.mock(StateDao.class);
        countryDao = mockery.mock(CountryDao.class);

        addressService = new AddressServiceImpl();

        addressService.addressDao = addressDao;
        addressService.stateDao = stateDao;
        addressService.countryDao = countryDao;
    }

    @Test
    public void testFindAddress() throws Exception {
        final Address address = new Address();
        mockery.checking(new Expectations(){{
            one(addressDao).find(1L);
            will(returnValue(address));
        }});

        assertEquals(address, addressService.findAddress(1L));
    }

    @Test
    public void testFindAllAddresses() throws Exception {
        final List<Address> addresses = Collections.emptyList();
        mockery.checking(new Expectations(){{
            one(addressDao).findAll();
            will(returnValue(addresses));
        }});

        final List<Address> all = addressService.findAllAddresses();
        assertNotNull(all.isEmpty());
        assertEquals(addresses, all);
    }

    @Test
    public void testPersist() throws Exception {
        final Address address = new Address();
        mockery.checking(new Expectations(){{
            one(addressDao).persist(address);
            will(returnValue(address));
        }});

        assertEquals(address, addressService.persist(address));
    }

    @Test
    public void testFindAllCountries() throws Exception {
        final List<Country> countries = Collections.emptyList();
        mockery.checking(new Expectations(){{
            one(countryDao).findAll();
            will(returnValue(countries));
        }});

        final List<Country> all = addressService.findAllCountries();
        assertNotNull(all.isEmpty());
        assertEquals(countries, all);
    }
    
    @Test
    public void testFindAllStates() throws Exception {
        final List<Province> provinces = Collections.emptyList();
        mockery.checking(new Expectations(){{
            one(stateDao).findAll();
            will(returnValue(provinces));
        }});

        final List<Province> all = addressService.findAllStates();
        assertNotNull(all.isEmpty());
        assertEquals(provinces, all);
    }
}
