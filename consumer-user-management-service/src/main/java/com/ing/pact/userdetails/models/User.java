package com.ing.pact.userdetails.models;


import lombok.Data;

@Data
public class User {
	 
	private String title;
	 
	private String firstn;
	 
	private String gender;
	 
	private String empid;
	  
	private Address address;
}
