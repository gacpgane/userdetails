package com.ing.usermanagement.repository;
 

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.ing.usermanagement.BaseTest;
import com.ing.usermanagement.model.User;
import com.ing.usermanagement.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test") 
public class UserManagementRepositoryTest extends BaseTest{

	@Autowired
	private UserRepository userRepository;
	 
	@Test
	public void shouldReturnUser(){		
		System.out.println("---------  Junit testing shouldReturnUser---------");
		Optional<User> userOptional=userRepository.findById(1l);
		User user = userOptional.orElse(null); 
		assertThat(user).isNotNull();
		assertThat(user.getAddress()).isNotNull();
		assertThat(user.getFirstName()).isNotNull();
		assertThat(user.getEmployeeId()).isNotNull();
		assertThat(user.getGender()).isNotNull();
	}
	
	@Test
	public void shouldUpdateUser(){
		System.out.println("---------  Junit testing shouldUpdateUser---------");
		Optional<User> userOptional=userRepository.findById(2l);
		User user = userOptional.orElse(null); 
	    String firstName=user.getFirstName();
	    String updatedFirstName=firstName+"updated";
	    user.setFirstName(updatedFirstName);
	    user=userRepository.save(user);
	    assertThat(user.getFirstName().equals(updatedFirstName)).isTrue();
	}
	
}
