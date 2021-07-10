package com.citi.loan;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.citi.loan.controller.BankController;
import com.citi.loan.model.LoginModel;
import com.citi.loan.model.LoginResponseModel;
import com.citi.loan.service.LoginService;

@SpringBootTest
@ActiveProfiles("test")
class SpringBootAppApplicationTests {	
	@Autowired
	private LoginService loginService;
	
	@BeforeAll
	public static void setup() {
		System.out.println("I am setup method..!");
		
	}
	
	
	@BeforeEach
	public void setupForEach() {
		System.out.println("I am setup for each method..!");
		
	}
	
	@Test
	public void testDeleteUser() {		
		LoginResponseModel loginModel = loginService.deleteUser("abcd", "test123");		
		assertEquals(loginModel.getStatusCode(), "200");
		assertEquals(loginModel.getStatusMessage(), "User abcd and pwd : test123 got deleted successfully");
	}
	
	@Test
	public void testingWithInvalidUserName() {
		LoginResponseModel loginModel = loginService.login("abcd", "test123");		
		assertEquals(loginModel.getStatusCode(), "200");
		assertEquals(loginModel.getStatusMessage(), "Failed");
	}
	
	@Test
	public void testingWithInvalidPassword() {
		LoginResponseModel loginModel = loginService.login("testadmin", "test1234");		
		assertEquals(loginModel.getStatusCode(), "200");
		assertEquals(loginModel.getStatusMessage(), "Failed");
	}
	
	@Test
	public void testingWithInvalidPasswordAndUserName() {
		LoginResponseModel loginModel = loginService.login("123admin", "test1234");		
		assertEquals(loginModel.getStatusCode(), "200");
		assertEquals(loginModel.getStatusMessage(), "Failed");
	}
	
	@Test
	public void testingWithCorrectCredentials() {
		LoginResponseModel loginModel = loginService.login("testadmin", "test123");		
		assertEquals(loginModel.getStatusCode(), "200");
		assertEquals(loginModel.getStatusMessage(), "Success");
	}
	
	@AfterEach
	public void resetForEach() {
		System.out.println("I am reset for each method..!");
	}
	@AfterAll
	public static void reset() {
		System.out.println("I am reset method..!");
	}

}
