package com.assignment.employee.serviceactivator;

/*import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.rmg.camel.exception.IrrecoverableException;
import com.rmg.camel.exception.RecoverableException;
import com.rmg.camel.exception.SampleRecoverableException;
import com.rmg.camel.exception.db.*;
import com.rmg.camel.utils.DateUtils;
import com.rmg.indicia.bean.LuhnModSixteenCheckDigitGenerator;
import com.rmg.indicia.circuitbreaker.*;
import com.rmg.indicia.exception.IndiciaNumberGenerationException;
import com.rmg.indicia.exception.IrrecoverableCheckDigitCalculationException;
import com.rmg.indicia.exception.IrrecoverableQueryException;
import com.rmg.indicia.exception.RecoverableQueryException;
import com.rmg.indicia.model.*;*/

import com.assignment.employee.DAO.MongoDBDeleteEmployee;
import com.assignment.employee.DAO.MongoDBGetEmployee;
import com.assignment.employee.DAO.MongoDBSaveEmployee;
import com.assignment.employee.DAO.MongoDBUpdateEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/*import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/

/*import static com.google.common.base.Preconditions.checkNotNull;
import static com.rmg.indicia.constants.IndiciaNumberServiceConstants.*;
import static com.rmg.indicia.exception.IndiciaErrorScenario.*;
import static com.rmg.indicia.model.IndiciaStatus.SPOILT;*/

/**
 * This is responsible for saving the details of a transaction to the database.
 *
 * @author Deniz Dalkilic
 * @author Abbas Attarwala
 * @author Rabia Karim
 */
@Component(value = "employeeService")
@Profile(value = "default")
public class EmployeeService {

    /*@Autowired
    @Qualifier(value = "indiciaSequenceTemplate")
    private MongoTemplate indiciaSequenceTemplate;*/
    
    @Autowired
    @Qualifier(value = "employeeDataTemplate")
    private MongoTemplate employeeDataTemplate;
    
    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public String saveEmployee(final String employeeJson) {
        // Save the employee data JSON

        LOGGER.debug("Attempting to save generated employee to the database - {}", employeeJson);

        try {
            new MongoDBSaveEmployee(employeeDataTemplate, employeeJson).save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeJson;
    }

    public String getEmployee(final String empId) {
        // Get the employee JSON
        String employeeJson = null;

        LOGGER.debug("Attempting to get employee from database - {}", empId);

        try {
            employeeJson = new MongoDBGetEmployee(employeeDataTemplate, empId).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeJson;
    }

    public void deleteEmployee(final String empId) {
        // Get the employee JSON
        boolean isDeleted = false;

        LOGGER.debug("Attempting to delete employee from database - {}", empId);

        try {
            new MongoDBDeleteEmployee(employeeDataTemplate, empId).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String updateEmployee(final String employeeJson) {
        // Save the employee data JSON

        LOGGER.debug("Attempting to update existing employee to the database - {}", employeeJson);

        try {
            new MongoDBUpdateEmployee(employeeDataTemplate, employeeJson).update();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeJson;
    }

            /**
             * Setter method for the MongoTemplate, enabling the class to be setup with Autowiring
             * @param employeeDataTemplate An implementation of the MongoTemplate
             */
    protected void setTemplate(MongoTemplate employeeDataTemplate) {
        this.employeeDataTemplate = employeeDataTemplate;
    }
}
