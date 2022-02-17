package com.ing.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

	private static final long serialVersionUID =1L;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("firstn")
	private String firstn;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("empid")
	private Long empid;
	
	@JsonProperty("address")
	private AddressDto addressDto;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstn() {
		return firstn;
	}

	public void setFirstn(String firstn) {
		this.firstn = firstn;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
