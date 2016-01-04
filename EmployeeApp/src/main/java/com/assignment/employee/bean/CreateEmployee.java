package com.assignment.employee.bean;

import com.assignment.employee.DAO.DataStore;
import com.assignment.employee.model.Employee;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chirjain on 12/21/2015.
 */
public class CreateEmployee {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateEmployee.class);

    public void createEmployee(Exchange exchange) throws Exception{

        Employee employee = exchange.getIn().getBody(Employee.class);

        LOGGER.info("Exchange body:: "+employee);

        DataStore.save(employee);
        //exchange.getIn().setBody("Employee created successfully");


    }
}
