package com.bank.bankofrupcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOfRupcyController {

	@GetMapping("/")
	public String lgp() {
		return "login";
	}
}
