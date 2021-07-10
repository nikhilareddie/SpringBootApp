package com.citi.loan.model;

/*
 {
 "user" : "Raja",
 "password" : "admin123"
 }
 <LoginModel>
 	<user>Raja</user>
 	<password>admin123</password>
 </LoginModel>
 */

public class LoginModel {
	
	private String user;
	
	private String password;
	
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
