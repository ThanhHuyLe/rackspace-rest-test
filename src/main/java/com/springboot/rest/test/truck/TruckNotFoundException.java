package com.springboot.rest.test.truck;

public class TruckNotFoundException extends RuntimeException {

	public TruckNotFoundException(String exception) {
		super(exception);
	}

}
