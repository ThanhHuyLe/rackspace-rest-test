package com.springboot.rest.test.vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {
	@Id
	private Long id;
	private String type;
	private String timestamp;
	public Vehicle() {
		super();
	}

	public Vehicle(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() { return type; }
	public void setType(String type) {
		this.type = type;
	}
	public String getTimestamp() { return timestamp;}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
