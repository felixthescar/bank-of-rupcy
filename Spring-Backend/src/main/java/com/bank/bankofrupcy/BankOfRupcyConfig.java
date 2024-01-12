package com.bank.bankofrupcy;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class BankOfRupcyConfig implements WebMvcConfigurer {
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET", "POST", "PUT", "OPTION", "DELETE")
		.allowedOrigins("http://localhost:3000");
	}
}
