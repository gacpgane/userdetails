package com.ing.usermanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ing.usermanagement.dto.ErrorDto;

/**
 * 
 * @author prabuddha
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorDto error = new ErrorDto("Server Error","SYS_000", details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(((MethodArgumentTypeMismatchException)ex).getName()+": "+((MethodArgumentTypeMismatchException)ex).getValue());
		ErrorDto error = new ErrorDto("Invalid Parameter Type","RC_001", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleMethodArgumentNotValidException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		 
		for (final FieldError error : ((BindException) ex).getBindingResult().getFieldErrors()) {
			details.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ((BindException) ex).getBindingResult().getGlobalErrors()) {
        	details.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
		
		ErrorDto error = new ErrorDto("Invalid Request","RC_002", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataBaseServiceUnavailableException.class)
	public final ResponseEntity<Object> DataBaseServiceUnavailableException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorDto error = new ErrorDto("Thanks for your request, We appologise for the issue, please come back later","SYS_000", details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorDto error = new ErrorDto("Record Not Found","RC_004", details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	 
}
