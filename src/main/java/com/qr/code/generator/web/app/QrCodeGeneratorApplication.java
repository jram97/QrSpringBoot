package com.qr.code.generator.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QrCodeGeneratorApplication extends SpringBootServletInitializer{
	
	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(QrCodeGeneratorApplication.class, args);

	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(QrCodeGeneratorApplication.class);

	}
}
