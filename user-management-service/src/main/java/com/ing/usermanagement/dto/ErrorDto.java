package com.ing.usermanagement.dto;

import java.util.List;
 
/**
 * 
 * @author prabuddha
 *
 */
public class ErrorDto {

	private String message;
	private String errorCode;
	private List<String> details;
	 
	public ErrorDto(String message, String errorCode,List<String> details) {
	    super();
	    this.message = message;
	    this.errorCode=errorCode;
	    this.details = details;
	   
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	 
	  
	
}
