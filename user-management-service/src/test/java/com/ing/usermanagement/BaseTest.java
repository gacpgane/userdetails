package com.ing.usermanagement;

import com.ing.usermanagement.dto.AddressDto;
import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.model.Address;
import com.ing.usermanagement.model.User;

public class BaseTest {

	public User getUser() {
		User user1 = new User();
		user1.setFirstName("Dani");
		user1.setTitle("Mr");
		user1.setGender("MALE");
		user1.setId(1l);
		user1.setEmployeeId("12222");
		Address address = new Address();
		address.setId(1);
		address.setCity("Darlington");
		address.setPostcode(1413);
		address.setState("NSW");
		address.setStreet("Abercrombie Street");
		user1.setAddress(address);
		return user1;
	}

	public UserDto getUserDto() {
		UserDto userDto = new UserDto();
		userDto.setEmpid("1234");
		userDto.setFirstn("Dayle");
		userDto.setGender("MALE");
		userDto.setTitle("MR");

		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Darlington");
		addressDto.setPostcode(1413);
		addressDto.setState("NSW");
		addressDto.setStreet("Abercrombie Street");
		userDto.setAddressDto(addressDto);
		return userDto;
	}

}