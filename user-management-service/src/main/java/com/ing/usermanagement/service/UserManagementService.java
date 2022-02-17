package com.ing.usermanagement.service;

import org.springframework.stereotype.Service;

import com.ing.usermanagement.dto.UserDto;


public interface UserManagementService {

	public UserDto updateUser(Long id,UserDto userDto);
	
	public UserDto getUser(Long id);
}
