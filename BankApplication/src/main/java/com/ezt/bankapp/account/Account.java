package com.ezt.bankapp.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@NamedQuery(name = "Account.findByHolderName", query = "select a from Account a where a.holderName=:holderName")


@Entity
@Table
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNo;
	@Column
	private String holderName;
	@Column	
	private Double balance;
	
	public Account() {		
	}

	public Account(String accountFolderName, Double accountBalance) {
		super();
		setHolderName(holderName);
		setBalance(balance);
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String accountFolderName) {
		this.holderName = accountFolderName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double accountBalance) {
		this.balance = accountBalance;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountFolderName=" + holderName + ", accountBalance="
				+ balance + "]";
	}
	
}
