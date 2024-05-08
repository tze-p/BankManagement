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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/* [Notes]
 * 1. In each @XXXMapping("../{xxx}"), "xxx" must match the method parameter name
 * 2. @Operation() - allows more info to be provided
 * 3. @ApiResponse() - response info, not sure how it works. Response doesn't seem to change
 */

@RequestMapping(path = "/account")
public interface AccountController {
	
	@ApiResponse( responseCode = "200", description = "Yippe, account created successfully!" )
	@PostMapping(path = "/create")
	public ResponseEntity<String> createAccount(@RequestBody Account account);
	
	@Operation( summary = " - get details of an account (approach 1)", description="**Note: Find account using Spring Data's findById() method. This is the preferred option." )
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable Long id);
	
	@Operation( summary = " - get details of an account (approach 2)", description="**Note: Find account using query findByAccountNumber defined in Account" )
	@GetMapping("/view/{accountNumber}")
	public Account getAccountByAccountNumber(@PathVariable Long accountNumber);
	
	@GetMapping("/getall")
	public List<Account> getAllAccountDetails();
	
	@PutMapping("/deposite/{accountNumber}/{money}")
	public ResponseEntity<String> depositMoney(@PathVariable Long accountNumber, @PathVariable Double money);
	
	@PutMapping("/withdraw/{accountNumber}/{money}")
	public ResponseEntity<String> withdrawMoney(@PathVariable Long accountNumber, @PathVariable Double money);
	
	@PutMapping("/close/{accountNumber}")
	public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber);
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id);
}

