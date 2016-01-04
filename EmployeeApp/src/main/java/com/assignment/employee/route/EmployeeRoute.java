package com.assignment.employee.route;

import com.assignment.employee.model.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * Created by chirjain on 12/21/2015.
 */
public class EmployeeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

         /**
         * Route for creating an Employee
         */
        from("cxfrs:bean:createEmployeeRESTServer?bindingStyle=SimpleConsumer")
                .routeId("CREATE EMPLOYEE ROUTE")
                .log("Received request for create employee --> ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .log("Body after marshalling --> ${body}")
                .beanRef("employeeService", "saveEmployee")
                //.beanRef("createEmployeeBean", "createEmployee")
                .log("Response --> ${body}")
                .marshal().json(JsonLibrary.Jackson);

        /**
         * Route for getting an Employee
         */
        from("cxfrs:bean:getEmployeeRESTServer?bindingStyle=SimpleConsumer")
                .routeId("GET EMPLOYEE ROUTE")
                .log("Received request for get employee --> ${body}")
                .beanRef("employeeService", "getEmployee")
                //.beanRef("getEmployeeBean", "getEmployee")
                .log("Response --> ${body}")
                .marshal().json(JsonLibrary.Jackson);

        /**
         * Route for deleting an Employee
         */
        /*from("cxfrs:bean:deleteEmployeeRESTServer?bindingStyle=SimpleConsumer")
                .routeId("DELETE EMPLOYEE ROUTE")
                .log("Received request for delete employee --> ${body}")
                .beanRef("employeeService", "deleteEmployee")
                .log("Response --> ${body}");*/

        /**
         * Route for updating an Employee
         */
        /*from("cxfrs:bean:updateEmployeeRESTServer?bindingStyle=SimpleConsumer")
                .routeId("UPDATE EMPLOYEE ROUTE")
                .log("Received request for update employee --> ${body}")
                .beanRef("employeeService", "updateEmployee")
                .log("Response --> ${body}")
                .marshal().json(JsonLibrary.Jackson);
                */

    }
}
