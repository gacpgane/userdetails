package com.ing.pact.userdetails.clients;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.pactfoundation.consumer.dsl.LambdaDsl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import com.ing.pact.userdetails.clients.UserDetailsServiceClient;
import com.ing.pact.userdetails.models.User;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserDetailsService")
class UserDetailsServiceClientPactTest {
	@Autowired
	private UserDetailsServiceClient userDetailsServiceClient;

	@Pact(consumer = "consumerUserDetails")
	public RequestResponsePact getUserDetails(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return builder.given("Get User Details").uponReceiving("valid user id from provider").method("GET")
				.path("/api/userdetails/1232854").willRespondWith().headers(headers).status(200)
				.matchHeader("Authorization", "Bearer [a-zA-Z0-9=\\+/]+", "Bearer AAABd9yHUjI=")
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

	@Test
	@PactTestFor(pactMethod = "getUserDetails")
	void testGetUserDetails(MockServer mockServer) {
		userDetailsServiceClient.setBaseUrl(mockServer.getUrl());
		User user = userDetailsServiceClient.getUserDetailsById(1232854l);
		User assertUser = new User();
		assertThat(user.getTitle(),is(equalTo("MR")));
	}

	@Pact(consumer = "consumerUserDetails")
	public RequestResponsePact noUser(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return builder.given("Get User Details").uponReceiving("invalid user id from provider").method("GET")
				.path("/api/userdetails/1232").willRespondWith().headers(headers).status(404)
				.body(LambdaDsl.newJsonBody((object) -> {
					object.stringType("message", "Record Not Found");
					object.stringType("errorCode", "RC_004");
					object.stringType("details", "Unable to find the user");
					object.array("details", (c) -> {
						c.stringValue("Unable to find the user");
					});
				}).build()).toPact();
	}

	@Test
	@PactTestFor(pactMethod = "noUser")
	void testNoUser(MockServer mockServer) {
		try{
		userDetailsServiceClient.setBaseUrl(mockServer.getUrl());
		User userDetails = userDetailsServiceClient.getUserDetailsById(1232);
		fail("Expected service call to throw an exception");
		}catch (HttpClientErrorException ex){
			assertThat(ex.getStatusCode(),is(equalTo(HttpStatus.NOT_FOUND)));
		}
	}

	@Pact(consumer = "consumerUserDetails")
	public RequestResponsePact noBasicAuth(PactDslWithProvider builder) {
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return builder.given("Get User Details").uponReceiving("invalid user credentials").method("GET")
				.path("/api/userdetails/1232854").willRespondWith().headers(headers).status(401).toPact();
	}

	@Test
	@PactTestFor(pactMethod = "noBasicAuth")
	void testNoBasicAuth(MockServer mockServer) {
		userDetailsServiceClient.setBaseUrl(mockServer.getUrl());
		try {
			User user = userDetailsServiceClient.getUserDetailsById(1232854l);
			fail("Expected service call to throw an exception");
		} catch (HttpClientErrorException ex) {
			assertThat(ex.getStatusCode(),is(equalTo(HttpStatus.UNAUTHORIZED)));
		}
	}
}
