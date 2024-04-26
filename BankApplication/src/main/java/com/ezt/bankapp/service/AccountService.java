package com.ezt.bankapp.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.ezt.bankapp.account.Account;


public interface AccountService {

	public ResponseEntity<String> createAccount(Account account);	
	
	public Account getAccountDetails(Long accountNumber);	
	
	public List<Account> getAllAccountDetails();
	
	public Account depositeAmount(Long accountNumber, Double money);
	
	public Account withdrawAmount(Long accountNumber, Double money);
	
	public void closeAccount(Long accountNumber);
}
