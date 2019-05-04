package com.test.bin;
import java.io.Serializable;

import javax.xml.bind.annotation.*;

@XmlRootElement (name = "student") 
public class Student implements Serializable  {
	private int id; 
	private String firstName;
	private String lastName;
	private int roll;
	private Address address;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(int id,String firstName, String lastName, int roll, Address address) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roll = roll;
		this.address=address;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getRoll() {
		return roll;
	}


	public void setRoll(int roll) {
		this.roll = roll;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	

}
