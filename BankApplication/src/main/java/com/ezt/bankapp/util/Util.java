package com.ezt.bankapp.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ezt.bankapp.account.Account;

public class Util {

	public static ResponseEntity<String> getResponseEntity(String message, HttpStatus status) {
		return new ResponseEntity<String>("{\"message\":\"" + message + "\" }", status);
	}
	
	public static void log(String message, Account account) { 		
		String tmpString = "";
		tmpString = tmpString.concat("holderName:" + account.getHolderName() + "; ");
		tmpString = tmpString.concat("balance:" + account.getBalance());		
		System.out.println(">> " + message + ":- " + tmpString);
	}
	
}
