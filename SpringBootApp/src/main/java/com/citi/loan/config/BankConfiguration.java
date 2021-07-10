package com.citi.loan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "bank.login")   //if model class and yml or properties file have same names then this will take care of it and read the values.
//without @ConfigurationProperties if the names are same in class and files it gives null and wont read the value so we use @ConfigurationProperties so that value can be read  
public class BankConfiguration {
	
	@Value("${bank.login.user}")
	private String user;	//this is diff from yml name
	@Value("${bank.login.password}")
	private String password;
	
	private String captcha;          
	
	
	
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
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
