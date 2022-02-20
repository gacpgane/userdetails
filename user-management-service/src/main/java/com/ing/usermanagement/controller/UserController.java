/**
 * 
 */
package com.ing.usermanagement.controller;

import javax.validation.Valid;

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

import com.ing.usermanagement.dto.UserDto;
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

	@GetMapping("/userdetails/{empid}")
	public ResponseEntity<Object> getUserDetails(@PathVariable("empid") long empId) {
		UserDto userDto = userManagementService.getUser(empId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PutMapping("/userdetails/{empid}")
	public ResponseEntity<Object> updateUserDetails(@PathVariable("empid") long empId,
			@Valid @RequestBody UserDto userDto) {
		UserDto dto = userManagementService.updateUser(empId, userDto);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

}
