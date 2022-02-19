package com.ing.usermanagement.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author prabuddha
 *
 */
@JsonInclude(Include.NON_NULL)
public class AddressDto {

	private static final long serialVersionUID=1L;
	
	@JsonProperty
	@Valid
	@NotNull(message = "Street is required")
	private String street;
	
	@JsonProperty
	@Valid
	@NotNull(message = "City is required")
	private String city;
	
	@JsonProperty
	@Valid
	@NotNull(message = "State is required")
	private String state;
	
	@JsonProperty
	@Valid
	@NotNull(message = "State is required")
	private Integer postcode;

	public AddressDto() {

	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

}
