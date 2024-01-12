package com.bank.bankofrupcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.bank.bankofrupcy.*")	
@ComponentScan(basePackages={"com.bank.*"})
@SpringBootApplication
public class BankOfRupcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankOfRupcyApplication.class, args);
		System.out.println("API edition");
	}

}
