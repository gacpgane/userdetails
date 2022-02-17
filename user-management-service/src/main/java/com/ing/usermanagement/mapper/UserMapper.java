/*package com.ing.usermanagement.mapper;
 
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ing.usermanagement.dto.AddressDto;
import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.model.Address;
import com.ing.usermanagement.model.User;

@Mapper(componentModel="spring")
public interface UserMapper {

	@Mapping(target="title",source ="title")
	@Mapping(target="firstName",source="firstn")
	@Mapping(target="gender",source="gender")
	@Mapping(target="employeeId",source="empid")
	User toUser(UserDto dto);
	
	@Mapping(target="street",source="street")
	@Mapping(target="city",source="city")
	@Mapping(target="state",source="state")
	@Mapping(target="postcode",source="postcode")
	Address toAddress(AddressDto dto);
	
	@Mapping(target ="title",source="title")
	@Mapping(target="firstn",source="firstName")
	@Mapping(target="gender",source="gender")
	@Mapping(target="empid",source="employeeId")
	UserDto toUserDto(User user);
	
	@Mapping(target="street",source="street")
	@Mapping(target="city",source="city")
	@Mapping(target="state",source="state")
	@Mapping(target="postcode",source="postcode")
	AddressDto toAddressDto(Address address);
	
	default User toUserModelObject(UserDto userDto){
		User user=toUser(userDto);
		return user;
	}
	
	default UserDto toUserDtoObject(User user){
		UserDto dto=toUserDto(user);
		return dto;
	}
	
	default Address toAddressModelObject(AddressDto addressDto){
		 Address address=toAddress(addressDto);
		 return address;
	}
	
	default AddressDto toAddressDtoObject(Address address){
		AddressDto dto=toAddressDto(address);
		return dto;
	}
}
*/