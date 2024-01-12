package com.bank.bankofrupcy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.bankofrupcy.model.Account;
import com.bank.bankofrupcy.repository.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	// Find all Accounts
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> allAccounts = accountRepository.findAll();
		return ResponseEntity.ok(allAccounts);
	}	

	// Find Account By Id
	public ResponseEntity<Account> findAccountById(Long id) {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			return ResponseEntity.ok(account.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Modify or Add Account
	public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
		Account newAccount = accountRepository.save(account);
		return ResponseEntity.ok(newAccount);
	}

	// Delete Account
	public ResponseEntity<String> deleteAccount(Long id) {
		accountRepository.deleteById(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	}

}