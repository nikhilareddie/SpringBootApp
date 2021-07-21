package com.citi.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.citi.loan.service.LoginService;

@SpringBootApplication
@ComponentScan(basePackages = "com.citi.loan")
public class SpringBootAppApplication {	

	public static void main(String[] args) {
		System.out.println("Hellow Spring Boot App");
		//SpringApplication.run(SpringBootAppApplication.class, args);
		
	   SpringApplication.run(SpringBootAppApplication.class, args);
	   //commented lines
	   
	   ////git_HUB something added both are not in sync
	   
			
	}

}
