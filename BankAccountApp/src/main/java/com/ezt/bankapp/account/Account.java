package com.ezt.bankapp.account;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@NamedQuery(name = "Account.findByHolderName",    query = "select a from Account a where a.holderName=:holderName")
@NamedQuery(name = "Account.findByAccountNumber", query = "select a from Account a where a.accountNo=:accountNo")

/* [Notes]
 * @Schema(description) - description appears under Schemas in: http://localhost:8082/swagger-ui/index.html#/
 * 
 */

@Entity
@Table
@Schema(description = "Bank accounts (MySQL)")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNo;
	@Column
	private String holderName;
	@Column	
	private Double balance;
	@Column(name="is_active")
	private boolean active = true;
	
	
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

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double accountBalance) {
		this.balance = accountBalance;
	}

	@Override
	public String toString() {
		return "accountNo: " + getAccountNo() + ", holderName: " + getHolderName() + ", balance: " + getBalance() + ", active: " + active;
	}
	
}
