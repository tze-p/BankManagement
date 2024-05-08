package com.ezt.bankapp.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.ezt.bankapp.account.Account;


public interface AccountService {

	public ResponseEntity<String> createAccount(Account account);	
	
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	
	public Account getAccountDetailsById(Long id);
	
	public List<Account> getAllAccountDetails();
	
	public ResponseEntity<String> depositMoney(Long accountNumber, Double money);
	
	public ResponseEntity<String> withdrawMoney(Long accountNumber, Double money);
	
	public ResponseEntity<String> closeAccount(Long accountNumber);
	
	public ResponseEntity<String> deleteAccount(Long accountNumber);
}
