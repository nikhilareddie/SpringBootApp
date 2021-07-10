package com.citi.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.loan.controller.BankController;
import com.citi.loan.model.LoginModel;
import com.citi.loan.model.LoginResponseModel;
import com.citi.loan.service.LoginService;



@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class BankControllerMockTesting {	
	@Mock
	private LoginService loginService;
	
	@InjectMocks
	private BankController controller;
	private static LoginModel loginModel;
	private static LoginResponseModel response = new LoginResponseModel();
	
	@BeforeAll
	public static void setup() {
		System.out.println("I am setup method..!");
		loginModel = new LoginModel();
		loginModel.setUser("12");
		loginModel.setPassword("23");
		
		response.setStatusCode("200");
		
	}
	
	
	
	@BeforeEach
	public void setupForEach() {
		System.out.println("I am setup for each method..!");
		
	}
	
	@Test
	public void testLoginUpdateInBankControllerUsingMockito() {
		response.setStatusMessage("Success");
		when(loginService.login(Mockito.anyString(), Mockito.anyString())).thenReturn(response);		
		ResponseEntity<LoginResponseModel> respEntity = controller.loginUpdate(loginModel);
		assertNotNull(respEntity);
		assertEquals(respEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testLoginUpdateInBankControllerUsingFailedResponseMockito() {	
		response.setStatusMessage("Failed");
		when(loginService.login(Mockito.anyString(), Mockito.anyString())).thenReturn(response);		
		ResponseEntity<LoginResponseModel> respEntity = controller.loginUpdate(loginModel);
		assertNotNull(respEntity);
		assertEquals(respEntity.getStatusCode(), HttpStatus.NO_CONTENT);
	}
}
