package com.springboot.rest.test.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
	@Id
	private Long id;
	private String numOfWheels;
	private String brandName;
	
	public Car() {
		super();
	}

	public Car(Long id, String numOfWheels, String brandName) {
		super();
		this.id = id;
		this.numOfWheels = numOfWheels;
		this.brandName = brandName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumOfWheels() {
		return numOfWheels;
	}
	public void setNumOfWheels(String numOfWheels) {
		this.numOfWheels = numOfWheels;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
		
}
