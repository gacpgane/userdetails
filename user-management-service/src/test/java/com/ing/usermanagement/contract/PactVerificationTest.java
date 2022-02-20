/*package com.ing.usermanagement.contract;
 

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.ing.usermanagement.model.User;
import com.ing.usermanagement.repository.UserRepository;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreMissingStateChange;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;

 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("UserDetailsService")
@PactFolder("pacts") 
@IgnoreMissingStateChange
@ActiveProfiles("test")
public class PactVerificationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setup(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void pactVerificationTestTemplate(PactVerificationContext context) {
		context.verifyInteraction();
	}
	
	@State(value = "valid user id from provider", action = StateChangeAction.SETUP)
	  void getUserDetails(Map<String, Object> params) {
	    long empId = ((Number) params.get("id")).longValue();
	    Optional<User> user = userRepository.findById(empId);
	    if (!user.isPresent()) {
	    	userRepository.save(user.get());
	    }
	  }
}
*/