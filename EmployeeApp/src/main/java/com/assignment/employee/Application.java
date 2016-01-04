package com.assignment.employee;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chirjain on 12/21/2015.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run("classpath:camel-server.xml", args);
    }


    @Bean
    public ServletRegistrationBean CXFServletRegistrationBean() {
        CXFServlet cxfServlet = new CXFServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(cxfServlet,"/*");
        //LOGGER.info("CXFServlet registered");
        return servletRegistrationBean;
    }
}
