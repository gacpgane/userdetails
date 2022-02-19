package com.ing.usermanagement.mapper;

import com.ing.usermanagement.dto.AddressDto;
import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.model.Address;
import com.ing.usermanagement.model.User;

/**
 * 
 * @author prabuddha
 *
 */
public class UserMapperUtil {

	public static User toUser(UserDto dto,User user) {
		user.setTitle(dto.getTitle());
		user.setEmployeeId(dto.getEmpid());
		user.setFirstName(dto.getFirstn());
		user.setGender(dto.getGender());
		
		Address address=new Address();
		address.setCity(dto.getAddressDto().getCity());
		address.setPostcode(dto.getAddressDto().getPostcode());
		address.setState(dto.getAddressDto().getState());
		
		user.setAddress(address);
 
		return user;
	}
	 
	
	public static UserDto toUserDto(User user,UserDto userDto) {
		userDto.setEmpid(user.getEmployeeId());
		userDto.setFirstn(user.getFirstName());
		userDto.setGender(user.getGender());
		userDto.setTitle(user.getTitle());
		
		AddressDto addressDto=new 
				AddressDto();
		addressDto.setCity(user.getAddress().getCity());
		addressDto.setPostcode(user.getAddress().getPostcode());
		addressDto.setState(user.getAddress().getState());
		addressDto.setStreet(user.getAddress().getStreet());
		userDto.setAddressDto(addressDto);
		return userDto;
	}
	
	 
}
