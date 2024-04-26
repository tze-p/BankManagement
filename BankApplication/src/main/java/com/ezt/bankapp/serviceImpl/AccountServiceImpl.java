package com.ezt.bankapp.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ezt.bankapp.account.Account;
import com.ezt.bankapp.repository.AccountDao;
import com.ezt.bankapp.service.AccountService;
import com.ezt.bankapp.util.Util;

// Must add @Service or will ge following error ...: 
// ...Field service in com.ezt.bankapp.restImpl.AccountControllerImpl required a bean of type 'com.ezt.bankapp.service.AccountService' that could not be found.
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao repository;

	/**
	 * Postman
	 * url: localhost:8082/account/create
	 * Body|raw: { "holderName":"Tze Poon", "balance":"88888" } 
	 */
	@Override
	public ResponseEntity<String> createAccount(Account account) {
		Util.log("In createAccount", account);
		try { 
			Account acc = repository.findByHolderName(account.getHolderName());
			if (!Objects.isNull(acc)) {
				return Util.getResponseEntity(
						String.format("Account with {%1$s} already exists", account.getHolderName()),
						HttpStatus.BAD_REQUEST);

			} else {
				repository.save(account);
				return Util.getResponseEntity(
						String.format("Successfully created account for %1$s with %1$s", account.getHolderName(), account.getBalance()), HttpStatus.OK);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return Util.getResponseEntity("Problem with account service", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Account getAccountDetails(Long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account depositeAmount(Long accountNumber, Double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		
	}

}
