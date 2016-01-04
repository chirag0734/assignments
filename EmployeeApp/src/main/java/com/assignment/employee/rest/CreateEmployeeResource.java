package com.assignment.employee.rest;

import com.assignment.employee.model.Employee;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by chirjain on 12/21/2015.
 */


public class CreateEmployeeResource {

    //final String APPLICATION_JSON_UTF_8 = MediaType.APPLICATION_JSON + "; " + MediaType.CHARSET_PARAMETER + "=utf-8";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(@NotNull(message = "Employee can't be null.")
                                   @Valid Employee employee) {
        //Actual response of this method will provided by the Camel route
        return null;
    }
}
