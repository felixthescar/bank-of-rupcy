package com.bank.bankofrupcy.model;

//import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cl_id", unique = true, nullable = false)
	private Long id;
	
	@JsonProperty
	@Column(name = "cl_name")
	private String name;
	
	@JsonProperty
	@Column(name = "cl_surname")
	private String surname;
	
	@JsonProperty
	@Column(name = "cl_num_personal_code")
	private String npc; // numeric personal code
	
	@JsonProperty
	@Column(name = "cl_phone_nr")
	private String phoneNr;

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
	
	public String toString() {
		return "Id: " + this.id + " Name: " + this.name + " Surname: " + this.surname + " NPC: " + this.npc + " Phone#: " + this.phoneNr;
	}
}
