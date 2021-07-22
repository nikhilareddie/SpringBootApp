package com.citi.loan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class BankServiceSecurityConfiguration extends WebSecurityConfigurerAdapter

{

	//private BCryptPasswordEncoder bCryptPasswordEncoder;

	//private UserDetailsService userDetailsService;

	private static final String[] AUTH_WHITELIST = {

			"bankuser"

	};

	//public BankServiceSecurityConfiguration(UserDetailsService userDetailsService,
			//BCryptPasswordEncoder bCryptPasswordEncoder)

	//{

	//	this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	//	this.userDetailsService = userDetailsService;

	//}

	protected void configure(HttpSecurity httpSecurity) throws Exception

	{

		httpSecurity.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers(HttpMethod.POST, "/bank/logincreate").permitAll()
				.antMatchers(HttpMethod.GET, "/bank/gerUserInfo").permitAll()
				.anyRequest().authenticated();

		// .and().addFilter(new AuthenticationFilter(authenticationManager()))

		// .addFilter(new AuthorizationFilter(authenticationManager()))

		// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception

	{

		authenticationManagerBuilder.inMemoryAuthentication().withUser("apiadmin").password("admin123").roles(AUTH_WHITELIST);

	}

	@Bean

	CorsConfigurationSource corsConfigurationSource()

	{

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

		return source;

	}

}