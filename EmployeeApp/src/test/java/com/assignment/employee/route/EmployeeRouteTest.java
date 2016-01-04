package com.assignment.employee.route;

import com.assignment.employee.serviceactivator.EmployeeService;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.direct.DirectEndpoint;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.MarshalDefinition;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chirjain on 12/23/2015.
 */

//@Category(AcceptanceTest.class)
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/camel-route-employeeTest.xml"})
public class EmployeeRouteTest{

    @Autowired
    @Qualifier(value = "context")
    protected ModelCamelContext context;

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @EndpointInject(uri = "direct:in")
    DirectEndpoint endPointInput;

    @Produce
    protected ProducerTemplate template;

    @Autowired
    EmployeeService mockService;

    final String message = "1";
    //final Map<String, Object> headers = new HashMap<String, Object>();

    @Before
    public void setup() throws Exception {
        RouteDefinition route = context.getRouteDefinition("CREATE EMPLOYEE ROUTE");

        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith(endPointInput);
                weaveByType(MarshalDefinition.class).after().to(resultEndpoint);
            }
        });

        context.start();

        resultEndpoint.reset();

        //reset(mockService);

        //headers.clear();
        //headers.put(BarcodeImageServiceConstants.PARAM_MESSAGE, message);
    }

    @Test
    @DirtiesContext
    public void whenEmployeeCreatedSuccessfully() throws InterruptedException {
        // Given
        final String input = "{\"empId\": 1,\"empName\": \"Chirag\",\"empDept\": \"DCX\"}";

        /*final BarcodeImageResponse expectedResponse = new BarcodeImageResponse();
        try {
            expectedResponse.setBase64BinaryImage(Files.readAllBytes(Paths.get("src/test/resources/1D.jpg")));
        } catch (IOException ioe) {
            fail("Unexpected Exception");
        }*/

        /*when(mockService.saveEmployee(message))
                .thenReturn(input);*/

        resultEndpoint.expectedMessageCount(1);

        // When
        template.sendBody(endPointInput, input);
        Thread.sleep(2000);

        /*// Expect
        resultEndpoint.assertIsSatisfied();
        assertFalse(resultEndpoint.getExchanges().isEmpty());

        final Exchange expectedExchange = resultEndpoint.getReceivedExchanges().get(0);
        assertNotNull(expectedExchange);

        CamelTestSupport.assertInMessageHeader(expectedExchange, Exchange.HTTP_RESPONSE_CODE, String.valueOf("201"));

        final String actualResponse = expectedExchange.getIn().getBody(String.class);

        assertNotNull(actualResponse);
        assertEquals(input, actualResponse);

        verify(mockService, times(1)).saveEmployee(message);*/
    }
}
