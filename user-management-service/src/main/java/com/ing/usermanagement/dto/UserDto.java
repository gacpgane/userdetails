package com.ing.usermanagement.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String firstn;
	private String gender;
	private String empid;

	private AddressDto addressDto;

	public UserDto() {

	}

	public UserDto(Long id, String title, String firstn, String gender, String empid, AddressDto addressDto) {
		super();
		this.id = id;
		this.title = title;
		this.firstn = firstn;
		this.gender = gender;
		this.empid = empid;
		this.addressDto = addressDto;
	}

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

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}
	
	

}
