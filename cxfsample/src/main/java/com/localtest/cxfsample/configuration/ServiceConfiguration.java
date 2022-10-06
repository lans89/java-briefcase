package com.localtest.cxfsample.configuration;

import com.localtest.cxfsample.endpoint.Baeldung;
import com.localtest.cxfsample.endpoint.ContinentService;
import com.localtest.cxfsample.interceptors.AppInboundInterceptor;
import com.localtest.cxfsample.interceptors.AppOutboundInterceptor;

import com.localtest.cxfsample.service.BaeldungImpl;
import com.localtest.cxfsample.service.ContinentServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
public class ServiceConfiguration {
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        springBus.getInInterceptors().add(new AppInboundInterceptor());
        springBus.getOutInterceptors().add(new AppOutboundInterceptor());
        return springBus;
    }

    @Bean
    public ServletRegistrationBean createBeanCxfDistpatcher() {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");
    }

    @Bean
    public Baeldung serviceGreeting(){
        return new BaeldungImpl();
    }

    @Bean
    public ContinentService serviceContinent(){
        return new ContinentServiceImpl();
    }


    @Bean
    public Endpoint endpointGreeting() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), serviceGreeting());
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/GreetService");
        return endpoint;
    }

    @Bean
    public Endpoint endpointContinent() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), serviceContinent());
        endpoint.getFeatures().add(new LoggingFeature());
        endpoint.publish("/ContinentService");
        return endpoint;
    }


}
