package com.jonevu.abi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonevu.abi.security.config.AuthorizationServerConfiguration;
import com.jonevu.abi.security.config.ResourceServerConfiguration;
import com.jonevu.abi.security.config.UmWebMvcConfiguration;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class JonevuServicesApplication {
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[] { JonevuServicesApplication.class,
        		AuthorizationServerConfiguration.class,
        		ResourceServerConfiguration.class,
        		UmWebMvcConfiguration.class}, args);
    }
}
