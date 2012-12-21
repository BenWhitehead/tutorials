package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Country;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Ben Whitehead
 */
@RunWith(JMock.class)
public class CountryDaoImplTest {

    private Mockery mockery;

    private EntityManager entityManager;

    private CountryDaoImpl countryDao;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();

        entityManager = mockery.mock(EntityManager.class);

        countryDao = new CountryDaoImpl();
        countryDao.em = entityManager;
    }

    @Test
    public void testFindAll() throws Exception {
        final List<Country> countries = Collections.emptyList();

        final TypedQuery<Country> mockQuery = mockery.mock(TypedQuery.class);
        mockery.checking(new Expectations(){{
            one(entityManager).createNamedQuery("country.find.all", Country.class);
            will(returnValue(mockQuery));
            one(mockQuery).getResultList();
            will(returnValue(countries));
        }});

        final List<Country> all = countryDao.findAll();
        assertNotNull(all);
        assertEquals(countries, all);
    }
}
