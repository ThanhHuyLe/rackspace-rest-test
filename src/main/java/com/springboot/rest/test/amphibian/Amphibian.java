package com.springboot.rest.test.amphibian;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Amphibian {
	@Id
	private Long id;
	private String speedPerHour;
	private String specie;
	
	public Amphibian() {
		super();
	}

	public Amphibian(Long id, String speedPerHour, String specie) {
		super();
		this.id = id;
		this.speedPerHour = speedPerHour;
		this.specie = specie;
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
	public void setSpeedPerHour(String speedPerHour) {
		this.speedPerHour = speedPerHour;
	}
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
		
}
