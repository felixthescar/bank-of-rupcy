package com.bank.bankofrupcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankofrupcy.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
