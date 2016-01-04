package com.assignment.employee.rest;

import com.assignment.employee.model.Employee;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by chirjain on 12/21/2015.
 */

public class GetEmployeeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(    @NotNull(message = "Employee ID cannnot be null")
                                    @QueryParam("empId") String empId) {
        //Actual response of this method will provided by the Camel route
        return null;
    }
}
