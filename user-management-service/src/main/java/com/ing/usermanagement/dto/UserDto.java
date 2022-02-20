package com.ing.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author prabuddha
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
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

}
