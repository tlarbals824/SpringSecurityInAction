package com.sim.ch06ex1.domain.authority.entity;

import com.sim.ch06ex1.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Authority {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@JoinColumn(name = "user")
	@ManyToOne
	private User user;

	public Authority() {
	}

	public Authority(Integer id, String name, User user) {
		this.id = id;
		this.name = name;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
