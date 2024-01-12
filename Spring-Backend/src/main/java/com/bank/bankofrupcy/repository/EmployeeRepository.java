package com.bank.bankofrupcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankofrupcy.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
