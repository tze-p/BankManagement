package com.ezt.bankapp.rest_controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ezt.bankapp.account.Account;


@RequestMapping(path = "/account")
public interface AccountController {
	
	@PostMapping(path = "/create")
	public ResponseEntity<String> createAccount(@RequestBody Account account);
	
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable Long id);
	
	@GetMapping("/view/{accountNumber}")
	public Account getAccountByAccountNumber(@PathVariable Long accountNumber);
	
	@GetMapping("/getall")
	public List<Account> getAllAccountDetails();
	
	@PutMapping("/deposite/{id}/{money}")
	public ResponseEntity<String> depositMoney(@PathVariable Long id, @PathVariable Double money);
	
	@PutMapping("/withdraw/{id}/{money}")
	public ResponseEntity<String> withdrawMoney(@PathVariable Long id, @PathVariable Double money);
	
	@PutMapping("/close/{id}")
	public ResponseEntity<String> closeAccount(@PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id);
}

