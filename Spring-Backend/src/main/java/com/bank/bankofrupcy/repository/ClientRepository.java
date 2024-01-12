package com.bank.bankofrupcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankofrupcy.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
