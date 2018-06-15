package com.springboot.rest.test.drone;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drone {
	@Id
	private Long id;
	private String cycleperMinute;
	private String brandName;
	
	public Drone() {
		super();
	}

	public Drone(Long id, String cyclePerMinute, String brandName) {
		super();
		this.id = id;
		this.cycleperMinute = cyclePerMinute;
		this.brandName = brandName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCycleperMinute() {
		return cycleperMinute;
	}
	public void setCycleperMinute(String cycleperMinute) {
		this.cycleperMinute = cycleperMinute;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
		
}
