package com.b2i.serviceorganisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
@EnableFeignClients
//@EnableDiscoveryClient 
public class ServiceOrganisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrganisationApplication.class, args);
	}

}
