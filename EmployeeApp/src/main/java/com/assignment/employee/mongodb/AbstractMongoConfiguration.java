package com.assignment.employee.mongodb;

import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chirjain on 12/30/2015.
 */
public abstract class AbstractMongoConfiguration extends org.springframework.data.mongodb.config.AbstractMongoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMongoConfiguration.class);
    @Value("${mongodb.write.concern:SAFE}")
    protected String writeConcern;
    @Value("${mongodb.username}")
    protected String username;
    @Value("${mongodb.password}")
    protected String password;
    @Value("${mongodb.replicaset.hostnames}")
    protected String replicaSetProperty;

    public AbstractMongoConfiguration() {
    }

    public Mongo mongo() throws Exception {
        List replicaSet = this.buildReplicaSet();
        MongoClient mongo;
        if(StringUtils.isBlank(this.username)) {
            LOGGER.info("MongoDb Authentication is disabled for {}", this.getDatabaseName());
            mongo = new MongoClient(replicaSet);
        } else {
            mongo = new MongoClient(replicaSet, this.getMongoCredentials());
        }

        mongo.setWriteConcern(WriteConcern.valueOf(this.getWriteConcern()));
        return mongo;
    }

    public abstract List<MongoCredential> getMongoCredentials();

    protected String getWriteConcern() {
        return this.writeConcern;
    }

    private List<ServerAddress> buildReplicaSet() {
        LOGGER.info("Building ServerAddress List for {}, {}", this.replicaSetProperty, this.getDatabaseName());
        ArrayList replicaSet = new ArrayList();
        List hostsAndPorts = Arrays.asList(this.replicaSetProperty.split(","));
        Iterator i$ = hostsAndPorts.iterator();

        while(i$.hasNext()) {
            String hostAndPort = (String)i$.next();
            String[] hostAndPortArray = hostAndPort.split(":");
            if(hostAndPortArray == null && hostAndPortArray.length != 2) {
                this.throwMongoConfigRuntimeException();
            }

            String host = hostAndPortArray[0];
            String port = hostAndPortArray[1];

            try {
                replicaSet.add(new ServerAddress(host, Integer.parseInt(port)));
            } catch (Exception var9) {
                this.throwMongoConfigRuntimeException();
            }
        }

        return replicaSet;
    }

    private void throwMongoConfigRuntimeException() {
        try {
            throw new Exception("Replica set should be a comma-separated list of <host>:<port> values; was " + this.replicaSetProperty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
