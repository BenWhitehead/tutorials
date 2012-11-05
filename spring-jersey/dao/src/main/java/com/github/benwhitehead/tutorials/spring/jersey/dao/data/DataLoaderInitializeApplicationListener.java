package com.github.benwhitehead.tutorials.spring.jersey.dao.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * This class is a spring ApplicationListener that calls
 * {@link com.github.benwhitehead.tutorials.spring.jersey.dao.data.DataLoader#initData()}
 * once spring has started up so that the data can be loaded into the in memory database.
 *
 * @author Ben Whitehead
 */
class DataLoaderInitializeApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        final ApplicationContext applicationContext = event.getApplicationContext();
        final DataLoader dataLoader = (DataLoader) applicationContext.getBean("dataLoader");
        dataLoader.initData();
    }
}
