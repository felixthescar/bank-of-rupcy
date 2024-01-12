package com.bank.bankofrupcy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankofrupcy.model.Account;
import com.bank.bankofrupcy.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	// Get all accounts
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return accountService.getAllAccounts();
	}

	// Get a account by ID
	@GetMapping("/getAccountById/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		System.out.println("somebody was here");
		ResponseEntity<Account> account = accountService.findAccountById(id);
		return account.hasBody() ? account : new ResponseEntity<Account>(new Account(), HttpStatus.NOT_FOUND);
	}

	// Create a new account
	@PostMapping("/createAccount")
	public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
	}

	// Modify an account
	@PostMapping("/modifyAccount")
	public ResponseEntity<Account> modifyAccount(@RequestBody Account account) {
		return account.getId() != null ? accountService.saveAccount(account)
				: new ResponseEntity<Account>(new Account(), HttpStatus.NOT_FOUND);
	}

	// Delete a Account
	@DeleteMapping(value = "/deleteAccount/{id}")
	public String deleteProduct(@PathVariable Long id) {
		ResponseEntity<Account> account = accountService.findAccountById(id);
		if (account.hasBody()) {
			accountService.deleteAccount(id);
			return "Account Deleted Successfully with id " + id + " ";
		}
		return "Account Not Found";
	}

}