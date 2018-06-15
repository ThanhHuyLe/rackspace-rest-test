package com.springboot.rest.test.truck;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Truck {
	@Id
	private Long id;
	private String speedPerHour;
	private String brandName;
	
	public Truck() {
		super();
	}

	public Truck(Long id, String speedPerHour, String brandName) {
		super();
		this.id = id;
		this.speedPerHour = speedPerHour;
		this.brandName = brandName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpeedPerHour() {
		return speedPerHour;
	}
	public void setSpeedPerHour(String numOfWheels) {
		this.speedPerHour = speedPerHour;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
		
}
