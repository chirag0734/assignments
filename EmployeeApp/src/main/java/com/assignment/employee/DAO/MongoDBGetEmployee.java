package com.assignment.employee.DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by chirjain on 12/31/2015.
 */
public class MongoDBGetEmployee {

    public static final String EMPLOYEE_FIELD_NAME = "empId";
    private final MongoTemplate employeeDataTemplate;
    private final String employeeId;

    private final Logger LOGGER = LoggerFactory.getLogger(MongoDBGetEmployee.class);

    public MongoDBGetEmployee(final MongoTemplate employeeDataTemplate,
                              final String employeeId) {

        this.employeeDataTemplate = employeeDataTemplate;
        this.employeeId = employeeId;
    }

    public String get() throws Exception {

        final DBCollection employeeCollection = employeeDataTemplate.getCollection("employee");

        final DBObject query = new BasicDBObject();

        if (employeeId != null) {
            query.put("empId", employeeId);
        }

        final DBObject result = employeeCollection.findOne(query);

        if (result == null) {
            throw new Exception("Getting an Employee from MongoDB Failed. " +
                    "No Rows Were Fetched for Query " + query.toString());
        }

        //LOGGER.debug("Employee fetched successfully from MongoDB. Result --> {}", result.toString());

        return result.toString();
    }
}
