package com.sim.ch11ex1s2.proxy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String username;
	private String password;
	private String code;

	@Builder
	public User(String username, String password, String code) {
		this.username = username;
		this.password = password;
		this.code = code;
	}
}
