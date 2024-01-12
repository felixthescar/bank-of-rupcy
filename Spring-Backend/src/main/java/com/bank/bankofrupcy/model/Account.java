package com.bank.bankofrupcy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cn_id", unique = true, nullable = false)
	private Long id;
	@Column(name = "cn_client_id_fk")
	private Long clientId;
	@Column(name = "cn_angajat_id_fk")
	private Long empId;
	@Column(name = "cn_balance")
	private Long balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public String toString() {
		return "Id: " + this.id + " ClId: " + this.clientId + " EmpId: " + this.empId + " Balance: " + this.balance;
	}
}
