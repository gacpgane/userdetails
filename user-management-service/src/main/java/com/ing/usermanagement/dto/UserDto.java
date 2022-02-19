package com.ing.usermanagement.dto;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
 
/**
 * 
 * @author prabuddha
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)

public class UserDto {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	@Valid
	@NotNull(message = "Title is required")
	private String title;
	
	@JsonProperty
	@Valid
	@NotNull(message = "First Name is required")
	private String firstn;
	
	@JsonProperty
	@Valid
	@NotNull(message = "Gender is required")
	private String gender;
	
	@JsonProperty
	@Valid
	@NotNull(message = "Employee ID is required")
	private String empid;

	@JsonProperty("address") 
	private AddressDto addressDto;

	public UserDto() {

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
