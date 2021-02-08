package org.example.daemonmodules;

import org.apache.commons.lang3.StringUtils;
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
    private static final String JVM_PROPERTY_NAME ="application.properties";

    private static Logger log = LoggerFactory.getLogger(ExampleDaemonModule.class);

    private Properties appProperties = new Properties();

    public ExampleDaemonModule() {
        String appPropertiesFile = System.getProperty(JVM_PROPERTY_NAME);
        if(StringUtils.isBlank(appPropertiesFile)) {
            log.error("JVM Property {} not set, not able to load properties", JVM_PROPERTY_NAME);
        } else {
            try {
                appProperties.load(new FileInputStream(appPropertiesFile));
            } catch (IOException e) {
                log.error("Not able to load properties from property file: {} Error: {}", appPropertiesFile, e.getMessage());
            }
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

