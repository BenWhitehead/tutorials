package com.github.benwhitehead.tutorials.angular.ee6.dao;

import com.github.benwhitehead.tutorials.angular.ee6.model.Province;
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
public class StateDaoImplTest {

    private Mockery mockery;

    private EntityManager entityManager;

    private StateDaoImpl stateDao;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();

        entityManager = mockery.mock(EntityManager.class);

        stateDao = new StateDaoImpl();
        stateDao.em = entityManager;
    }

    @Test
    public void testFindAll() throws Exception {
        final List<Province> provinces = Collections.emptyList();

        final TypedQuery<Province> mockQuery = mockery.mock(TypedQuery.class);
        mockery.checking(new Expectations(){{
            one(entityManager).createNamedQuery("province.find.all", Province.class);
            will(returnValue(mockQuery));
            one(mockQuery).getResultList();
            will(returnValue(provinces));
        }});

        final List<Province> all = stateDao.findAll();
        assertNotNull(all);
        assertEquals(provinces, all);
    }
}
