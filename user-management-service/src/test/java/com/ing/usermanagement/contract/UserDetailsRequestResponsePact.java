/*package com.ing.usermanagement.contract;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;


import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.pactfoundation.consumer.dsl.LambdaDsl; 

 
@ExtendWith(PactConsumerTestExt.class) 
public class UserDetailsRequestResponsePact {
	
	 

	@Pact(consumer = "INGUser")
	public RequestResponsePact getUserDetails(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return builder.given("Get User Details").uponReceiving("valid user id from provider").method("GET")
				.path("/api/userdetails/{id}").willRespondWith().headers(headers).status(200)
				.body(LambdaDsl.newJsonBody((object) -> {
					object.stringType("title", "MR");
					object.stringType("firstn", "test");
					object.stringType("lastname", "tsetlast");
					object.stringType("gender", "male");
					object.stringType("empid", "1232854");
					object.object("address", (object1) -> {
						object1.stringType("street", "12345 holling rd");
						object1.stringType("city", "Sydney");
						object1.stringType("state", "nsw");
						object1.numberValue("postcode", 2000);
					});
				}).build()).toPact();
	}
	
	*//**
	 * this can be achieved by the mock server too.
	 * @throws IOException
	 *//*
	@Test
	@PactTestFor(pactMethod = "getUserDetails")
	public void testGetUserDetailsProvider(MockServer mockServer) throws IOException {
		
		RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();
		//UserDetailService user = new UserDetailService(restTemplate).getUser("1");
 
	}

	 
}
*/