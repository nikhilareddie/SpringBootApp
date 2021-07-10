package com.citi.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.citi.loan.model.LoginModel;
import com.citi.loan.model.LoginResponseModel;

@SpringBootTest
@ActiveProfiles("test")
public class WebServiceTest {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	private static LoginModel loginModel;

	private LoginResponseModel loginResp;

	@BeforeAll
	public static void setup() {
		System.out.println("I am setup method..!");
		loginModel = new LoginModel();
		loginModel.setUser("devadmin");
		loginModel.setPassword("dev123");

	}

	@Test
	public void testLoginWebService() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LoginModel> reqEntity = new HttpEntity<LoginModel>(loginModel, headers);
		ResponseEntity<LoginResponseModel> respEntity = restTemplate().exchange("http://localhost:8080/bank/logincreate",
				HttpMethod.POST, reqEntity, LoginResponseModel.class);
		loginResp = respEntity.getBody();
		assertEquals(loginResp.getStatusCode(), "200");
		assertEquals(loginResp.getStatusMessage(), "Success");
	}

}
