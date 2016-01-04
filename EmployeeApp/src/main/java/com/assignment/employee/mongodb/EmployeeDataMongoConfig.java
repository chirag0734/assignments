package com.assignment.employee.mongodb;

import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration("employeeDataMongoConfig")
public class EmployeeDataMongoConfig extends AbstractMongoConfiguration{

    @Value("${mongodb.employeeData.dbname}")
    protected String dbName;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    @Bean(name = "employeeDataTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }

    @Override
    public List<MongoCredential> getMongoCredentials() {
        final MongoCredential credential = MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray());
        final List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);
        return credentials;
    }
}
