package com.ing.usermanagement.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author prabuddha
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class AddressDto {

	private static final long serialVersionUID = 1L;

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

}
