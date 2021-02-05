package org.example.daemonmodules;

import org.onehippo.repository.modules.DaemonModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExampleDaemonModule implements DaemonModule {
    // Java system property that defines the path to the application properties file.
    private static final String APPLICATION_PROPERTIES_FILE="application.properties";

    private static Logger log = LoggerFactory.getLogger(ExampleDaemonModule.class);

    private Properties appProperties = new Properties();

    public ExampleDaemonModule() {
        String appPropertiesFile = System.getProperty(APPLICATION_PROPERTIES_FILE);
        try {
            appProperties.load(new FileInputStream(appPropertiesFile));
        } catch (IOException e) {
            log.error("Not able to load properties from property file: {} Error: {}", appPropertiesFile, e.getMessage());
        }
    }

    @Override
    public void initialize(Session session) throws RepositoryException {
        // Print all properties
        appProperties.stringPropertyNames().forEach(key -> log.info("Key {} - Value {}", key, appProperties.getProperty(key)));
        log.info("Module initialized");
    }

    @Override
    public void shutdown() {
        log.info("Module shutdown executed");
    }

}

