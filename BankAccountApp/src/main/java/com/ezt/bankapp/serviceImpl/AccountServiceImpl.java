package com.ezt.bankapp.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ezt.bankapp.account.Account;
import com.ezt.bankapp.repo_dao.AccountDao;
import com.ezt.bankapp.service.AccountService;


// Must add @Service else will get following error ...: 
// ...Field service in com.ezt.bankapp.restImpl.AccountControllerImpl required a bean of type 'com.ezt.bankapp.service.AccountService' that could not be found.
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao repo;

	/**
	 * Postman
	 * POST: localhost:8082/account/create
	 * Body|raw: { "holderName":"Tze Poon", "balance":"88888" } 
	 */
	@Override
	public ResponseEntity<String> createAccount(Account account) {
		try { 
			Account acc = repo.findByHolderName(account.getHolderName());
			if (!Objects.isNull(acc)) {
				return new ResponseEntity<String>(
						"\"" + String.format("annot create account - account already exists: [%s]\n", acc.toString()) + "\" }",
						HttpStatus.BAD_REQUEST);

			} else {
				repo.save(account);
				return new ResponseEntity<String>(
						"\"" + String.format("Account successfully created\n[%s]\n", account.toString()) + "\" }",
						HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return new ResponseEntity<String>("\"Problem with account service\"", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * GET: localhost:8082/account/view/5
	 * 
	 * Find using standard findById() that returns an optional object
	 * Use isEmpty() to check if valid 
	 */
	@Override
	public Account getAccountDetailsById(Long id) {
		Optional<Account> account = repo.findById(id);
		if (account.isEmpty()) {
			throw new RuntimeException(String.format("Account %s does not exist", id));
		}
		Account rtn = account.get(); // get account
		return rtn;
	}

	/**
	 * GET: localhost:8082/account/7
	 * 
	 * Find using query defined in Account
	 * Use Objects.isNull() to check if valid
	 */
	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Account rtn = repo.findByAccountNumber(accountNumber);
		if (Objects.isNull(rtn)) {
			throw new RuntimeException(String.format("Account %s does not exist", accountNumber));
		}
		return rtn;
	}
	
	/**
	 * GET: localhost:8082/account/getall
	 */
	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> accounts = repo.findAll();
		return accounts;
	}

	/**
	 * PUT: localhost:8082/account/deposit/7/500
	 */
	@Override
	public ResponseEntity<String>  depositMoney(Long accountNumber, Double money) {
		Optional<Account> tmpAcc = repo.findById(accountNumber);
		if (tmpAcc.isEmpty()) {
			return new ResponseEntity<String>(
					"\"" + String.format("Account [%s] does not exists\n", accountNumber) + "\" }",
					HttpStatus.BAD_REQUEST);			
		}
		Account acc = tmpAcc.get();
		if (!acc.isActive()) {
			return new ResponseEntity<String>(
					"\"" + String.format("Account [%s] is closed\n", accountNumber) + "\" }",
					HttpStatus.BAD_REQUEST);						
		}
		Double prevBalance = acc.getBalance();
		acc.setBalance(prevBalance + money);
		repo.save(acc);
		return new ResponseEntity<String>(
				"{\"response\":\"" + String.format("Amount %s successfully deposited\nAccount updated:[%s]\n", prevBalance, acc.toString()) + "\" }",
				HttpStatus.OK);
	}

	/**
	 * PUT: localhost:8082/account/withdraw/7/500000
	 */
	@Override
	public ResponseEntity<String> withdrawMoney(Long accountNumber, Double money) {
		Account acc = repo.findByAccountNumber(accountNumber);
		if (Objects.isNull(acc)) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot withdraw - account [%s] does not exists\n", accountNumber) + "\" }",
					HttpStatus.BAD_REQUEST);			
		}
		if (!acc.isActive()) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot withdraw - account [%s] is closed\n[%s]", accountNumber, acc.toString()) + "\" }",
					HttpStatus.BAD_REQUEST);						
		}		
		Double prevBalance = acc.getBalance();
		if (prevBalance < money) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot withdraw - balance is less than %s\n[%s]", money, acc.toString()) + "\"",
					HttpStatus.BAD_REQUEST);			
		}		
		acc.setBalance(prevBalance - money);
		repo.save(acc);
		return new ResponseEntity<String>(
				"\"" + String.format("Amount %s successfully withdrawn\nAccount updated:[%s]\n", prevBalance, acc.toString()) + "\" }",
				HttpStatus.OK);
	}

	/**
	 * PUT: localhost:8082/account/close/2
	 */
	@Override
	public ResponseEntity<String> closeAccount(Long accountNumber) {
		Account acc = repo.findByAccountNumber(accountNumber);
		if (Objects.isNull(acc)) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot close - account [%s] does not exists\n", accountNumber) + "\" }",
					HttpStatus.BAD_REQUEST);
		}
		if (!acc.isActive()) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot close - Account [%s] is already closed\n[%s]", accountNumber, acc.toString()) + "\" }",
					HttpStatus.BAD_REQUEST);
		}
		acc.setActive(false);
		repo.save(acc);
		return new ResponseEntity<String>(
				"\"" + String.format("Account successfully closed\nAccount updated:[%s]\n", acc.toString()) + "\" }",
				HttpStatus.OK);		
	}

	/** 
	 * DELETE: localhost:8082/account/delete/2
	 */
	@Override
	public ResponseEntity<String> deleteAccount(Long accountNumber) {
		Account acc = repo.findByAccountNumber(accountNumber);
		if (Objects.isNull(acc)) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot delete - account [%s] does not exists\n", accountNumber) + "\" }",
					HttpStatus.BAD_REQUEST);
		}
		if (acc.isActive()) {
			return new ResponseEntity<String>(
					"\"" + String.format("Cannot delete - account [%s] is still active\n[%s]", accountNumber, acc.toString()) + "\" }",
					HttpStatus.BAD_REQUEST);
		}
		repo.delete(acc);
		return new ResponseEntity<String>(
				"\"" + String.format("Account [%s] successfully deleted\n", accountNumber) + "\" }",
				HttpStatus.OK);				
	}
	
}
