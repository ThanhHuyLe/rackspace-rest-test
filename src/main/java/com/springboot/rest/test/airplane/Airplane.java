package com.springboot.rest.test.airplane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Airplane {
	@Id
	private Long id;
	private String speedPerMinute;
	private String airline;
	
	public Airplane() {
		super();
	}

	public Airplane(Long id, String speedPerMinute, String airline) {
		super();
		this.id = id;
		this.speedPerMinute = speedPerMinute;
		this.airline = airline;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpeedPerMinute() {
		return speedPerMinute;
	}
	public void setSpeedPerMinute(String speedPerMinute) {
		this.speedPerMinute = speedPerMinute;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
		
}
