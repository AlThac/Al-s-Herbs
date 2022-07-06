package com.caseStudy.newFinal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Product {
	private Long id;
	@Column(nullable=false, unique=true, length=45)
	private String name;
	@Column(nullable=false, length=20)
	private String uses;
	@Column(nullable=false, length=20)
	private String vitamins;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	public String getUses() {
		return uses;
	}
	public void setUses(String uses) {
		this.uses = uses;
	}
	public String getVitamins() {
		return vitamins;
	}
	public void setVitamins(String vitamins) {
		this.vitamins = vitamins;
	}
	
}
