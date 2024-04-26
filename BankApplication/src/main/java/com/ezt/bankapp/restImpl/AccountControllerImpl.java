package com.ezt.bankapp.restImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ezt.bankapp.account.Account;
import com.ezt.bankapp.repository.AccountDao;
import com.ezt.bankapp.rest.AccountController;
import com.ezt.bankapp.service.AccountService;

// must add @RestController or will get 404 "Not found"
@RestController
public class AccountControllerImpl implements AccountController {

	@Autowired
	AccountService service;

	@Autowired
	AccountDao dao;

	@Override
	public Account createAccount(Account account) {
		service.createAccount(account);
		Account rtn = dao.findByHolderName(account.getHolderName());
		return rtn;
	}

}
