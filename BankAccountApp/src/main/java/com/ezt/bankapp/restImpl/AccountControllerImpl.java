package com.ezt.bankapp.restImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ezt.bankapp.account.Account;
import com.ezt.bankapp.repo_dao.AccountDao;
import com.ezt.bankapp.rest_controller.AccountController;
import com.ezt.bankapp.service.AccountService;

import io.swagger.v3.oas.annotations.tags.Tag;


/* [Notes]
 * 1. must add @RestController or will get 404 "Not found"
 * 2. @Tag() - modifies the label shown in OpenAPI definition page, replaces "Account-Controller-Impl"
 */

@RestController
@Tag(name = "Account Controller CRUD API", description="Created by Tze Poon")
public class AccountControllerImpl implements AccountController {

	@Autowired
	AccountService service;

	@Autowired
	AccountDao dao;

	@Override
	public ResponseEntity<String> createAccount(Account account) {
		return service.createAccount(account);
	}

	@Override
	public Account getAccountByAccountNumber(Long accountNumber) {
		return service.getAccountDetailsByAccountNumber(accountNumber);
	}

	@Override
	public Account getAccountById(Long id) {
		return service.getAccountDetailsById(id);
	}

	@Override
	public List<Account> getAllAccountDetails() {
		return service.getAllAccountDetails();
	}

	@Override
	public ResponseEntity<String> depositMoney(Long id, Double money) {
		return service.depositMoney(id, money);		
	}

	@Override
	public ResponseEntity<String> withdrawMoney(Long id, Double money) {
		return service.withdrawMoney(id, money);
	}

	@Override
	public ResponseEntity<String> closeAccount(Long id) {
		return service.closeAccount(id);
	}

	@Override
	public ResponseEntity<String> deleteAccount(Long id) {
		return service.deleteAccount(id);
	}
	
}
