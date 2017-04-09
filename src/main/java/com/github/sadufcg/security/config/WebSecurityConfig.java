package com.github.sadufcg.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Student's answers POST
				.antMatchers(HttpMethod.POST, "/answer/**").permitAll().and().authorizeRequests()
				// Students answers GET
				.antMatchers(HttpMethod.GET, "/answer/**").permitAll()
				// Questions GET
				.antMatchers(HttpMethod.GET, "/question").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/question").permitAll()
				.antMatchers(HttpMethod.GET, "/questionnaire").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/questionnaire").permitAll()
				.antMatchers(HttpMethod.GET, "/question/**").permitAll()
				.antMatchers(HttpMethod.GET, "/questionnaire/**").permitAll()
				// H2 Console:
				.antMatchers("/console/**").permitAll()
				// Any other request:
				.anyRequest().authenticated().and().httpBasic();
		// Disable CSRF
		http.csrf().disable();
		// Disable to H2 Console:
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
	}
}
