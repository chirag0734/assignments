package com.assignment.employee.DAO;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

/**
 * Created by chirjain on 12/31/2015.
 */
public class MongoDBSaveEmployee {

    public static final String EMPLOYEE_FIELD_NAME = "empId";
    private final MongoTemplate employeeDataTemplate;
    private final String employeeDataToSave;

    private final Logger LOGGER = LoggerFactory.getLogger(MongoDBSaveEmployee.class);

    public MongoDBSaveEmployee(final MongoTemplate employeeDataTemplate,
                                final String employeeDataJson) {

        this.employeeDataTemplate = employeeDataTemplate;
        this.employeeDataToSave = employeeDataJson;
    }

    public String save() throws Exception {

        final DBObject employee = (DBObject) JSON.parse(employeeDataToSave);

        final DBCollection employeeCollection = employeeDataTemplate.getCollection("employee");

        final DBObject query = new BasicDBObject();
        query.put(EMPLOYEE_FIELD_NAME, employee.get(EMPLOYEE_FIELD_NAME));

        final WriteResult result = employeeCollection.update(query, employee, true, false);

        if (result == null || result.getN() <= 0) {
            throw new Exception("Saving Employee to MongoDB Failed. " +
                    "No Rows Were Updated for Query " + query.toString());
        }

        //LOGGER.debug("Employee saved successfully to MongoDB. Result --> {}", result.toString());

        return result.toString();
    }
}
