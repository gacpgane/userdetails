/**
 * 
 */
package com.ing.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.usermanagement.dto.ErrorDto;
import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.exception.ServiceApiException;
import com.ing.usermanagement.service.UserManagementService;
 
/**
 * @author prabuddha
 *
 */
@RestController
@RequestMapping("/api")
@Validated
public class UserController {

	@Autowired
	private UserManagementService userManagementService;

	@GetMapping("/userdetails/{id}")
	public ResponseEntity<Object> getUserDetails(@PathVariable("id") long id) {
			UserDto userDto = userManagementService.getUser(id);
			return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PutMapping("/userdetails/{id}")
	public ResponseEntity<Object> updateUserDetails(@PathVariable("id") long id, @RequestBody UserDto userDto) {	 
			UserDto dto=userManagementService.updateUser(id, userDto);	
			return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	
	 
}
