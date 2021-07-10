package com.citi.loan.controller;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RestController
//@Controller
@RestControllerAdvice  //used for exception handler
@RequestMapping("/bank")
public class BankController {

	
	private static final Logger log  = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private LoginService loginService;

	//creations(post)
	@RequestMapping(method = RequestMethod.POST, path = "/logincreate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<LoginResponseModel> login(@RequestBody LoginModel loginModel) {
		log.info(loginModel.getUser() + " : " + loginModel.getPassword());
		LoginResponseModel respofstatus = loginService.login(loginModel.getUser(), loginModel.getPassword());
		ResponseEntity<LoginResponseModel> respEntity = null;
		if (respofstatus.getStatusMessage().equalsIgnoreCase("success")) {
			respEntity = new ResponseEntity<LoginResponseModel>(respofstatus, HttpStatus.CREATED);
		} else {
			respEntity = new ResponseEntity<LoginResponseModel>(respofstatus, HttpStatus.NO_CONTENT);
		}

		return respEntity;
	}

	//update(put)
	@RequestMapping(method = RequestMethod.PUT, path = "/loginDataUpdate", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<LoginResponseModel> loginUpdate(@RequestBody LoginModel loginModel) {
		log.debug(loginModel.getUser() + " : " + loginModel.getPassword());
		LoginResponseModel respModel = loginService.login(loginModel.getUser(), loginModel.getPassword());
		ResponseEntity<LoginResponseModel> respEntity = null;
		if (respModel.getStatusMessage().equalsIgnoreCase("success")) {
			respEntity = new ResponseEntity<LoginResponseModel>(respModel, HttpStatus.OK);
		} else {
			respEntity = new ResponseEntity<LoginResponseModel>(respModel, HttpStatus.NO_CONTENT);
		}

		return respEntity;
	}

	//retreving data
	@RequestMapping(method = RequestMethod.GET, path = "/gerUserInfo", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<LoginModel> userInfo(@RequestParam(value  = "name", required = true) String name,
			@RequestParam(value = "pwd", required = false, defaultValue = "12345") String pwd) {
		log.warn(name + " : " + pwd);
		//int a = 1/0;
		LoginModel respModel = loginService.getUserInfo();  //getUserInfo method is of type LoginModel
		ResponseEntity<LoginModel> respEntity = new ResponseEntity<LoginModel>(respModel, HttpStatus.FOUND);

		return respEntity;
	}

	
	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteUserInfo", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<LoginResponseModel> deleteUserInfo(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "pwd", required = false, defaultValue = "12345") String pwd) throws FileNotFoundException {
		log.error(name + " : " + pwd);
		LoginResponseModel respModel = loginService.deleteUser(name, pwd);
		//int a = 1 / 0;
		ResponseEntity<LoginResponseModel> respEntity = new ResponseEntity<LoginResponseModel>(respModel,
				HttpStatus.GONE);
		
		//throw new FileNotFoundException();  //throwing the exception

		return respEntity;
	}
	
	
	/*
	 * @ExceptionHandler({ArithmeticException.class,FileNotFoundException.class})
	 * //Array of Exceptions that is 2 exceptions... public
	 * ResponseEntity<LoginResponseModel> handleError(HttpServletRequest req,
	 * Exception ex) {
	 * 
	 * LoginResponseModel respModel = new LoginResponseModel(); respModel.
	 * setStatusMessage("Custom Arithmatic or Filenot Exception message : Divide by zero exception"
	 * ); respModel.setStatusCode("400"); ResponseEntity<LoginResponseModel>
	 * respEntity = new ResponseEntity<LoginResponseModel>(respModel,
	 * HttpStatus.EXPECTATION_FAILED); return respEntity; }
	 * 
	 * 
	 * @ExceptionHandler(Exception.class) public ResponseEntity<LoginResponseModel>
	 * commonHandleError(HttpServletRequest req, Exception ex) {
	 * 
	 * LoginResponseModel respModel = new LoginResponseModel(); respModel.
	 * setStatusMessage("Custom Common Exception message : Divide by zero exception"
	 * ); respModel.setStatusCode("400"); ResponseEntity<LoginResponseModel>
	 * respEntity = new ResponseEntity<LoginResponseModel>(respModel,
	 * HttpStatus.EXPECTATION_FAILED); return respEntity; }
	 */
	
}
