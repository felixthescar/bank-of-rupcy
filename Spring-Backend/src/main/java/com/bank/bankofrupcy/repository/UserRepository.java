package com.bank.bankofrupcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankofrupcy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
