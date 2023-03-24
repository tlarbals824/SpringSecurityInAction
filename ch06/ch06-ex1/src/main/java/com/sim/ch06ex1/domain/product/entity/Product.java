package com.sim.ch06ex1.domain.product.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private double price;

	@Enumerated(EnumType.STRING)
	private Currency currency;

	public Product() {
	}

	public Product(Integer id, String name, double price, Currency currency) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.currency = currency;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
