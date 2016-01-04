package com.assignment.employee.rest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by chirjain on 12/21/2015.
 */

public class DeleteEmployeeResource {

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(    @NotNull(message = "Employee ID cannnot be null")
                                    @QueryParam("empId") String empId) {
        //Actual response of this method will provided by the Camel route
        return null;
    }
}
