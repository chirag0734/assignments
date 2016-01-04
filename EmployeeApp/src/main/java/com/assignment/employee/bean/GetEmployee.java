package com.assignment.employee.bean;

import com.assignment.employee.DAO.DataStore;
import com.assignment.employee.model.Employee;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chirjain on 12/21/2015.
 */

public class GetEmployee {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetEmployee.class);

    public void getEmployee(Exchange exchange) throws Exception{

        //int empId = (Integer)exchange.getIn().getBody();

        //LOGGER.info("Exchange body:: "+empId);

        LOGGER.info("Exchange body:: "+exchange.getIn().getBody());
        Integer empId = Integer.parseInt((String)exchange.getIn().getHeader("empId"));

        LOGGER.info("empId:: "+empId);

        Employee employee = DataStore.get(empId);

        LOGGER.info("employee:: "+employee);

        exchange.getIn().setBody(employee);
    }

}
