package com.citi.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.citi.loan.config.BankConfiguration;
import com.citi.loan.model.LoginModel;
import com.citi.loan.model.LoginResponseModel;

@Service
public class LoginService {
	//three methods login,deleteUser,getUserInfo...using model classes which are LoginResponseModel,LoginModel
	
	@Autowired
	private BankConfiguration bankConfiguration;
	
	public LoginResponseModel login(String user, String password) {
		System.out.println("==================login method fired");
		LoginResponseModel response = new LoginResponseModel();
		if(bankConfiguration.getUser().equalsIgnoreCase(user) && bankConfiguration.getPassword().equals(password)) {
			response.setStatusCode("200");
			response.setStatusMessage("Success");
			System.out.println(bankConfiguration.getCaptcha());
		}else {
			response.setStatusCode("200");
			response.setStatusMessage("Failed");
		}
		return response;
	}
	
	public LoginResponseModel deleteUser(String user, String password) {
		LoginResponseModel response = new LoginResponseModel();
		
			response.setStatusCode("200");
			response.setStatusMessage("User "+user+" and pwd : "+password+" got deleted successfully");
		
		return response;
	}
	
	public LoginModel getUserInfo() {
		LoginModel lm = new LoginModel();
		lm.setUser("King");
		lm.setPassword("1234abc");
		return lm;
	}

}
