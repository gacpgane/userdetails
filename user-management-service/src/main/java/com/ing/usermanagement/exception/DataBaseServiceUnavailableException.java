package com.ing.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author prabuddha
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DataBaseServiceUnavailableException extends RuntimeException {

	public DataBaseServiceUnavailableException(String exception) {
		super(exception);
	}

}
