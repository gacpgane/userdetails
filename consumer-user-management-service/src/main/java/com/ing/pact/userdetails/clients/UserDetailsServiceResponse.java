package com.ing.pact.userdetails.clients;

import lombok.Data;

import java.util.List;

import com.ing.pact.userdetails.models.User;

@Data
public class UserDetailsServiceResponse {
  private User userDetails;
}
