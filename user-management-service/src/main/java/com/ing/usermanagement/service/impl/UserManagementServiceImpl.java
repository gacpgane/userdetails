package com.ing.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.exception.ResourceNotFoundException;
import com.ing.usermanagement.exception.DataBaseServiceUnavailableException;
import com.ing.usermanagement.mapper.UserMapperUtil;
import com.ing.usermanagement.model.Address;
import com.ing.usermanagement.model.User;
import com.ing.usermanagement.repository.UserRepository;
import com.ing.usermanagement.service.UserManagementService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * 
 * @author prabuddha
 *
 */

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 
	 */
	@Override
	@CircuitBreaker(name = "updateUser", fallbackMethod = "updateUserFallback")
	@Transactional
	public UserDto updateUser(Long empId, UserDto userDto) {
		User user = userRepository.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Unable to find the user"));
		user.setTitle(userDto.getTitle());
		user.setFirstName(userDto.getFirstn());
		user.setGender(userDto.getGender());
		user.setEmployeeId(Long.valueOf(userDto.getEmpid()));
		Address address = user.getAddress();
		if (address != null) {
			address.setCity(userDto.getAddressDto().getCity());
			address.setPostcode(userDto.getAddressDto().getPostcode());
		}
		user = userRepository.save(user);
		return UserMapperUtil.toUserDto(user, new UserDto());
	}

	@Override
	@CircuitBreaker(name = "getUser", fallbackMethod = "getUserFallback")
	public UserDto getUser(Long empId) {

		User user = userRepository.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Unable to find the user"));
		UserDto userDto = UserMapperUtil.toUserDto(user, new UserDto());
		return userDto;
	}

	public UserDto updateUserFallback(Throwable t) throws Throwable {
		if (t instanceof ResourceNotFoundException) {
			throw t;
		}
		throw new DataBaseServiceUnavailableException("Circuit Breaker-updateUserFallback");
	}

	public UserDto getUserFallback(Throwable t) throws Throwable {
		if (t instanceof ResourceNotFoundException) {
			throw t;
		}
		throw new DataBaseServiceUnavailableException("Circuit Breaker-getUserFallback");
	}

}
