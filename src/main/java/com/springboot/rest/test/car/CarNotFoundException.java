package com.springboot.rest.test.car;

public class CarNotFoundException extends RuntimeException {

	public CarNotFoundException(String exception) {
		super(exception);
	}

}
