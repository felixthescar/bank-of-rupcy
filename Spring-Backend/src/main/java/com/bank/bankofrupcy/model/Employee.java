package com.bank.bankofrupcy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "angajat")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "an_id", unique = true, nullable = false)
	private Long id;
	@Column(name = "an_name")
	private String name;
	@Column(name = "an_surname")
	private String surname;
	@Column(name = "an_num_personal_code")
	private String npc; // numeric personal code
	@Column(name = "an_phone_nr")
	private String phoneNr;
	@Column(name = "an_salary")
	private String salary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNpc() {
		return npc;
	}

	public void setNpc(String npc) {
		this.npc = npc;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String toString() {
		return "Id: " + this.id + " Name: " + this.name + " Surname: " + this.surname + " NPC: " + this.npc + " Phone#: " + this.phoneNr + " Salary: " + this.salary;
	}

}
