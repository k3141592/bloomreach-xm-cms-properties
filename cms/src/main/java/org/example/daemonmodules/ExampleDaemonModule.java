package org.example.daemonmodules;

import org.onehippo.repository.modules.DaemonModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

public class ExampleDaemonModule implements DaemonModule {
    private static Logger log = LoggerFactory.getLogger(ExampleDaemonModule.class);

    @Override
    public void initialize(Session session) throws RepositoryException {
        log.info("Module initialized");
    }

    @Override
    public void shutdown() {
        log.info("Module shutdown executed");
    }

}

