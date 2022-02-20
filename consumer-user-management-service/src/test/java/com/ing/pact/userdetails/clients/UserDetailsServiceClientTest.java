package com.ing.pact.userdetails.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.ing.pact.userdetails.clients.UserDetailsServiceClient;
import com.ing.pact.userdetails.clients.UserDetailsServiceResponse;
import com.ing.pact.userdetails.models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockResolver.Wiremock;
import ru.lanwen.wiremock.ext.WiremockUriResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver.WiremockUri;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith({ WiremockResolver.class, WiremockUriResolver.class })
class UserDetailsServiceClientTest {
  
  @Autowired
  private UserDetailsServiceClient userDetailsServiceClient;


  @Test
  void getUserDetailsById(@Wiremock WireMockServer server, @WiremockUri String uri) {
    userDetailsServiceClient.setBaseUrl(uri);
    server.stubFor(
      get(urlPathEqualTo("/api/userdetails/1232856"))
        .willReturn(aResponse()
          .withStatus(200)
          .withBody("{\n" +
            "            \"title\": \"mrs\",\n" +
            "            \"firstn\": \"Rossie\",\n" +
            "            \"gender\": \"female\",\n" +
            "            \"empid\": \"1232856\",\n" +
            "            \"address\":{\n" +
            "                         \"street\": \"Charlotte Street\",\n" +
            "                         \"city\": \"Brisbane\",\n" +
            "                         \"street\": \"Charlotte Street\",\n" +
            "                         \"state\": \"QlD\",\n" +
            "                         \"postcode\": 4413 \n" +
            "                         }"+
            "        }\n")
          .withHeader("Content-Type", "application/json"))
    );

     
   
    User user = userDetailsServiceClient.getUserDetailsById(1232856);
    assertThat(user.getFirstn(), is(equalTo("Rossie")));
  }
}
