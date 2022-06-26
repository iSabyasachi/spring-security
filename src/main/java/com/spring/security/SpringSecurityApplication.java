package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityApplication {
	/*
	//Class
	AuthenticationFilter authenticationFilter;
	//Interface
	AuthenticationManager authenticationManager;
	//Interface
	AuthenticationProvider authenticationProvider;
	//Interface
	UserDetailsService detailsService;
	//Interface
	PasswordEncoder encoder;
	//Interface
	SecurityContext context;
	//Interface
	Authentication authentication;*/
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
