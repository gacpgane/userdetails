package com.ing.usermanagement.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ing.usermanagement.BaseTest;
import com.ing.usermanagement.dto.UserDto;

import net.minidev.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest extends BaseTest{

	@LocalServerPort
	private int port;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void getUserDetails() throws MalformedURLException {
		log.debug("-------- UserControllerTest getUserDetails -----------");
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<UserDto> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/api/userdetails/1232854").toString(), UserDto.class);
		assertEquals(response.getBody().getEmpid(),"1232854");
		
	}

	@Test
	public void updateUserDetails() throws RestClientException, MalformedURLException {
		RestTemplate restTemplate= new RestTemplate();
		 
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    JSONObject user = new JSONObject();
	     
	    user.put("title", "MR");
	    user.put("firstn", "John");
	    user.put("gender", "MALE");
	    user.put("empid", "23234");
	    
	    JSONObject address = new JSONObject();
	    address.put("street", "Tallong");
	    address.put("city", "Tollong");
	    address.put("state", "AU");
	    address.put("postcode",new Integer (343));
	    user.put("address",address );
	    
	    HttpEntity<String> request = 
	    	      new HttpEntity<String>(user.toString(), headers);
	     
	    restTemplate.put(new URL("http://localhost:" + port + "/api/userdetails/1232855").toString(), request, ""); 
		
	    ResponseEntity<UserDto> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/api/userdetails/23234").toString(), UserDto.class);
	    UserDto dto=getUserDto();
		 
	    assertEquals(response.getBody().getFirstn(),"John");
		assertEquals(response.getBody().getEmpid(),"23234");
		
	}
	
	
	 

}
