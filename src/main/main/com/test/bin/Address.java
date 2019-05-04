package com.test.bin;

import java.io.Serializable;

public class Address implements Serializable {
	
	private int houseNo;
	private String streetName;
	private int pin; 

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	
	

	public Address(int houseNo, String streetName, int pin) {
		super();
		this.houseNo = houseNo;
		this.streetName = streetName;
		this.pin = pin;
	}

	public Address() {
		
 		
	}

}
