package com.citi.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.loan.service.LoginService;

@Component
public class CommandLineRunnerApp implements org.springframework.boot.CommandLineRunner {
    //@Autowired
    //private LoginService loginService;

    @Override
    public void run(String...args) throws Exception {
    //	String result = loginService.login("admin", "Pwd123#");
		//System.out.println("Login result "+result);	

    }
}
