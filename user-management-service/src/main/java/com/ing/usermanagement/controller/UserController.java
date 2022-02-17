/**
 * 
 */
package com.ing.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserController {

	@Autowired
	private UserManagementService userManagementService;

	@GetMapping("/userdetails/{id}")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") long id) {
		UserDto userDto = userManagementService.getUser(id);
		if (userDto != null) {
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} else {
			// throw not found exception.
			return null;
		}
	}

	@PutMapping("/userdetails/{id}")
	public ResponseEntity<UserDto> updateUserDetails(@PathVariable("id") long id, @RequestBody UserDto userDto) {
		UserDto dto=userManagementService.updateUser(id, userDto);	
		if (userDto != null) {
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} else {
			// throw not found exception.
			return null;
		}
	}

}
