package com.ing.usermanagement.exception;

import org.springframework.http.HttpStatus;

public class ServiceApiException extends RuntimeException {

	private String errorCode;
	private HttpStatus status;

	public ServiceApiException(HttpStatus status,String errorCode, String errorDesc, Throwable cause) {
		super(errorDesc, cause);
		this.errorCode = errorCode;
		this.status=status;

	}
	
	public ServiceApiException(HttpStatus status,String errorCode, String errorDesc) {
		super(errorDesc);
		this.errorCode = errorCode;
		this.status=status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
