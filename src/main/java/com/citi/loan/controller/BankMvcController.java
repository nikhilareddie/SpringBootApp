package com.citi.loan.controller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citi.loan.model.LoginModel;
import com.citi.loan.model.LoginResponseModel;
import com.citi.loan.service.LoginService;



//http://localhost:8080/bank/login
//@RestController
@Controller
@RestControllerAdvice  //used for exception handler
@RequestMapping("/bank")
public class BankMvcController {

	
	private static final Logger log  = LoggerFactory.getLogger(BankMvcController.class);

	@Autowired
	private LoginService loginService;

	//creations(post)
	@RequestMapping(method = RequestMethod.POST, path = "/login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.TEXT_HTML_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String login(@RequestParam(value  = "userName", required = true) String name,
			@RequestParam(value = "password", required = true) String password) {
		log.info(name + " : " + password);
		LoginResponseModel respofstatus = loginService.login(name, password);
		

		log.info(respofstatus.getStatusCode() + " : " + respofstatus.getStatusMessage());
		return "<h1> Login "+ respofstatus.getStatusMessage() +"</h1>";
	}

	
}
