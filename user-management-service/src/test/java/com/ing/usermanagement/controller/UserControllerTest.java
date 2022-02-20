package com.ing.usermanagement.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;

import com.ing.usermanagement.dto.UserDto;

import net.minidev.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void getUserDetails() throws MalformedURLException {
		log.debug("-------- UserControllerTest getUserDetails -----------");
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<UserDto> response = testRestTemplate.withBasicAuth(
		  "user123", "user123").getForEntity(new URL("http://localhost:" + port + "/api/userdetails/1232854").toString(), 
				  UserDto.class);
		  
		assertEquals(response.getBody().getEmpid(),"1232854");
		
	}
	
	@Test
	public void getUserDetails401() throws MalformedURLException {
		log.debug("-------- UserControllerTest getUserDetails 401 -----------");
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		 
		ResponseEntity<String> response = testRestTemplate.withBasicAuth(
		  "user123", "user").getForEntity(new URL("http://localhost:" + port + "/api/userdetails/1232854").toString(), 
				  String.class);
		 
		assertEquals(response.getStatusCode(),HttpStatus.UNAUTHORIZED);
		
	}

	@Test
	public void updateUserDetails() throws RestClientException, MalformedURLException {
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	   
	    JSONObject user = new JSONObject();
	    user.put("title", "MR");
	    user.put("firstn", "John123");
	    user.put("gender", "MALE");
	    user.put("empid", "1232855");
	    
	    JSONObject address = new JSONObject();
	    address.put("street", "Tallong");
	    address.put("city", "Tollong");
	    address.put("state", "AU");
	    address.put("postcode",new Integer (343));
	    user.put("address",address );
	    
	    HttpEntity<String> request = 
	    	      new HttpEntity<String>(user.toString(), headers);
	    
	    testRestTemplate.withBasicAuth(
		  		  "admin", "admin").put(new URL("http://localhost:" + port + "/api/userdetails/1232855").toString(), request, ""); 
		
	    ResponseEntity<UserDto> response = testRestTemplate.withBasicAuth(
		  		  "admin", "admin")
				.getForEntity(new URL("http://localhost:" + port + "/api/userdetails/1232855").toString(), UserDto.class);
	     
	    assertEquals(response.getBody().getFirstn(),"John123");
		
	}
	
}
