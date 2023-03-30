package com.sim.ch10ex3.domain.Token.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String identifier;
	private String token;

	public Token() {
	}

	public Token(int id, String identifier, String token) {
		this.id = id;
		this.identifier = identifier;
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
