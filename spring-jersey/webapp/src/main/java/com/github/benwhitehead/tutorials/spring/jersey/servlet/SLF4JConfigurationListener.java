package com.github.benwhitehead.tutorials.spring.jersey.servlet;

import org.jetbrains.annotations.Nullable;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This is context listener to activate the java util logging over slf4j when the application starts up.
 * See <a href="http://www.slf4j.org/legacy.html#jul-to-slf4j">http://www.slf4j.org/legacy.html#jul-to-slf4j</a>
 */
public class SLF4JConfigurationListener implements ServletContextListener {

    public void contextInitialized(@Nullable final ServletContextEvent sce) {
        SLF4JBridgeHandler.install();
    }

    public void contextDestroyed(@Nullable final ServletContextEvent sce) {
        SLF4JBridgeHandler.uninstall();
    }
}
