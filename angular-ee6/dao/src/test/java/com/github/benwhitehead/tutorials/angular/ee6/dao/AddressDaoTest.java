package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Address;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Ben Whitehead
 */
@RunWith(JMock.class)
public class AddressDaoTest {

    private Mockery mockery;

    private EntityManager entityManager;

    private AddressDaoImpl addressDao;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();

        addressDao = new AddressDaoImpl();

        entityManager = mockery.mock(EntityManager.class);
        addressDao.em = entityManager;
    }

    @Test
    public void testFindById() throws Exception {
        final Address result = new Address();
        mockery.checking(new Expectations(){{
            one(entityManager).find(Address.class, 1L);
            will(returnValue(result));
        }});

        assertEquals(result, addressDao.find(1L));
    }

    @Test
    public void testFindById_notFound() throws Exception {
        mockery.checking(new Expectations() {{
            one(entityManager).find(Address.class, 1L);
            will(returnValue(null));
        }});

        assertNull(addressDao.find(1L));
    }

    @Test
    public void testFindAll() throws Exception {
        final TypedQuery<Address> mockQuery = mockery.mock(TypedQuery.class);
        mockery.checking(new Expectations() {{
            one(entityManager).createNamedQuery("address.find.all", Address.class);
            will(returnValue(mockQuery));
            one(mockQuery).getResultList();
            will(returnValue(Collections.emptyList()));
        }});

        assertTrue(addressDao.findAll().isEmpty());
    }

}
