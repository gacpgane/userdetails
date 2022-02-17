package com.ing.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.usermanagement.dto.AddressDto;
import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.mapper.UserMapperUtil;
import com.ing.usermanagement.model.Address;
import com.ing.usermanagement.model.User;
import com.ing.usermanagement.repository.UserRepository;
import com.ing.usermanagement.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService{

	@Autowired 
	private UserRepository userRepository;
	
	 
	
	/**
	 * 
	 */
	@Override
	public UserDto updateUser(Long id,UserDto userDto) {
		User user= userRepository.getById(id);
		if(user!=null){
			user.setTitle(userDto.getTitle());
			user.setFirstName(userDto.getFirstn());
			user.setGender(userDto.getGender());
			user.setEmployeeId(userDto.getEmpid());
			Address address=user.getAddress();
			if(address!=null){
				address.setCity(userDto.getAddressDto().getCity());
				address.setPostcode(userDto.getAddressDto().getPostcode());
			}
		}
		user=userRepository.save(user);
		return UserMapperUtil.toUserDto(user,new UserDto());
	}

	
	@Override
	public UserDto getUser(Long id) {
		// TODO Auto-generated method stub
		User user= userRepository.getById(id);
		if(user!=null){
			UserDto userDto=UserMapperUtil.toUserDto(user,new UserDto());
			//UserDto dto=new UserDto();
			//dto.setEmpid(user.getEmployeeId());
			//dto.setFirstn(user.getFirstName());
			//dto.setGender(user.getGender());
			//dto.setTitle(user.getTitle());
			
			//set user address
			//AddressDto addressDto=userMapper.toAddressDto(user.getAddress());
			//AddressDto addressDto=new AddressDto();
			
			//addressDto.setCity(user.getAddress().getCity());
			//addressDto.setPostcode(user.getAddress().getPostcode());
			//addressDto.setState(user.getAddress().getState());
			//addressDto.setStreet(user.getAddress().getStreet());
			//userDto.setAddressDto(addressDto);
			return userDto;
		}
		
		//throw usernot found
		return null;
	}

}
