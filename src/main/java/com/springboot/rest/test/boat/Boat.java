package com.springboot.rest.test.boat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Boat {
	@Id
	private Long id;
	private String numOfEngines;
	private String brandName;
	
	public Boat() {
		super();
	}

	public Boat(Long id, String numOfEngines, String brandName) {
		super();
		this.id = id;
		this.numOfEngines = numOfEngines;
		this.brandName = brandName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumOfEngines() {
		return numOfEngines;
	}
	public void setNumOfEngines(String numOfEngines) {
		this.numOfEngines = numOfEngines;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
		
}
