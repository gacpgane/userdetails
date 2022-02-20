package com.ing.usermanagement.service;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.base.Optional;
import com.ing.usermanagement.BaseTest;
import com.ing.usermanagement.dto.UserDto;
import com.ing.usermanagement.exception.ResourceNotFoundException;
import com.ing.usermanagement.model.User;
import com.ing.usermanagement.repository.UserRepository;
import com.ing.usermanagement.service.impl.UserManagementServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UsermanagementServiceTest extends BaseTest{

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserManagementService service = new UserManagementServiceImpl();

	@BeforeEach
	public void setup() {
		Mockito.when(userRepository.findByEmployeeId(1232854l)).thenReturn(getUser());
		Mockito.when(userRepository.findByEmployeeId(2232854l)).thenThrow(EntityNotFoundException.class);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser().get());
	}

	@Test
	public void shouldGetUser() {
		System.out.println("-------------shouldGetUser------------------");
		UserDto dto;
		if (service != null) {
			dto = service.getUser(1232854l);
		} else {
			dto = null;
		}
		assertThat(dto).isNotNull();
		assertThat(dto.getAddressDto()).isNotNull();
		assertThat(dto.getAddressDto().getCity()).isNotNull();
		assertThat(dto.getAddressDto().getPostcode()).isNotNull();
		assertThat(dto.getAddressDto().getState()).isNotNull();
		assertThat(dto.getAddressDto().getStreet()).isNotNull();

		assertThat(dto.getFirstn()).isNotNull();
		assertThat(dto.getGender()).isNotNull();
		assertThat(dto.getTitle()).isNotNull();
		assertThat(dto.getEmpid()).isNotNull();

	}

	@Test
	public void shouldGetUserNotFound() {
		try {
			UserDto dto = service.getUser(223l);
		} catch (ResourceNotFoundException e) {
			Assertions.assertThat(e.getMessage().equals("Unable to find the user")).isTrue();
		}

	}

	@Test
	public void shouldUpdateUser() {
		UserDto dto = getUserDto();
		dto = service.updateUser(1232854l, dto);
		assertThat(dto).isNotNull();
		assertThat(dto.getAddressDto()).isNotNull();
		assertThat(dto.getAddressDto().getCity()).isNotNull();
		assertThat(dto.getAddressDto().getPostcode()).isNotNull();
		assertThat(dto.getAddressDto().getState()).isNotNull();
		assertThat(dto.getAddressDto().getStreet()).isNotNull();

		assertThat(dto.getFirstn()).isNotNull();
		assertThat(dto.getGender()).isNotNull();
		assertThat(dto.getTitle()).isNotNull();
		assertThat(dto.getEmpid()).isNotNull();

	}

	
}
