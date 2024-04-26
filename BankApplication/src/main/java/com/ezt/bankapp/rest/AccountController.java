package com.ezt.bankapp.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezt.bankapp.account.Account;


@RestController
@RequestMapping(path = "/account")
public interface AccountController {
	
	@PostMapping(path = "/create")
	public Account createAccount(@RequestBody Account account);
	
}
