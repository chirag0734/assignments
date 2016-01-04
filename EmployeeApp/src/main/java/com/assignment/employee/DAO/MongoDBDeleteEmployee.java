package com.assignment.employee.DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by chirjain on 12/31/2015.
 */
public class MongoDBDeleteEmployee {

    public static final String EMPLOYEE_FIELD_NAME = "empId";
    private final MongoTemplate employeeDataTemplate;
    private final String employeeId;

    private final Logger LOGGER = LoggerFactory.getLogger(MongoDBDeleteEmployee.class);

    public MongoDBDeleteEmployee(final MongoTemplate employeeDataTemplate,
                                 final String employeeId) {

        this.employeeDataTemplate = employeeDataTemplate;
        this.employeeId = employeeId;
    }

    public void delete() throws Exception {

        final DBCollection employeeCollection = employeeDataTemplate.getCollection("employee");

        final BasicDBObject query = new BasicDBObject();

        if (employeeId != null) {
            query.put("empId", employeeId);
        }

        final DBObject resultBefore = employeeCollection.findOne(query);

        LOGGER.info("resultBefore --> {}", resultBefore.toString());

        employeeCollection.remove(query);

        final DBObject resultAfter = employeeCollection.findOne(query);

        LOGGER.info("resultAfter --> {}", resultAfter.toString());






        //LOGGER.debug("Employee fetched successfully from MongoDB. Result --> {}", result.toString());


    }
}
