package com.ing.usermanagement.dto;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author prabuddha
 *
 */
@Data
public class ErrorDto {

	private String message;
	private String errorCode;
	private List<String> details;

	public ErrorDto(String message, String errorCode, List<String> details) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.details = details;

	}
}
