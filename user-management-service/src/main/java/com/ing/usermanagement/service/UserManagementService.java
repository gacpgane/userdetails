package com.ing.usermanagement.service;

import org.springframework.stereotype.Service;

import com.ing.usermanagement.dto.UserDto;

/**
 * 
 * @author prabuddha
 *
 */
public interface UserManagementService {

	public UserDto updateUser(Long empId, UserDto userDto);

	public UserDto getUser(Long empId);
}
