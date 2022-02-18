package com.ing.usermanagement.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDto {

	private Long id;
	private String street;
	private String city;
	private String state;
	private Integer postcode;

	public AddressDto() {

	}

	public AddressDto(Long id, String street, String city, String state, Integer postcode) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
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
