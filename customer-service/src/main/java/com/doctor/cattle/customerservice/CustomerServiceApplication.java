package com.doctor.cattle.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
//@ComponentScan(basePackages= {"com.doctor.cattle.customerservice.controller"})
//@EntityScan(basePackages= {"com.doctor.cattle.customerservice.entity"})
//@EnableJpaRepositories(basePackages= {"com.doctor.cattle.customerservice.repository"})
//@ComponentScan(basePackages= {"com.doctor.cattle.customerservice.service"})
//@ComponentScan(basePackages={"com.doctor.cattle.customerservice.security.*"})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
