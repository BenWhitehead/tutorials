package com.github.benwhitehead.tutorials.spring.jersey.dao.data;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Ben Whitehead
 */
@RunWith(JMock.class)
public class DataLoaderInitializeApplicationListenerTest {

    private Mockery mockery;
    private DataLoaderInitializeApplicationListener dataLoaderInitializeApplicationListener;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        mockery.setImposteriser(ClassImposteriser.INSTANCE);

        dataLoaderInitializeApplicationListener = new DataLoaderInitializeApplicationListener();
    }

    @Test
    public void testOnApplicationEvent() throws Exception {
        final ApplicationContext applicationContext = mockery.mock(ApplicationContext.class);
        final ContextRefreshedEvent event = new ContextRefreshedEvent(applicationContext);
        final DataLoader dataLoader = mockery.mock(DataLoader.class);
        mockery.checking(new Expectations(){{
            one(applicationContext).getBean("dataLoader");
            will(returnValue(dataLoader));
            one(dataLoader).initData();
        }});
        dataLoaderInitializeApplicationListener.onApplicationEvent(event);
    }
}
