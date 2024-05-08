package com.ezt.bankapp.repo_dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ezt.bankapp.account.Account;

public interface AccountDao extends JpaRepository<Account, Long> {

	Account findByHolderName(@Param("holderName") String holderName);
	
	Account findByAccountNumber(@Param("accountNo") Long accountNo);
	
}
