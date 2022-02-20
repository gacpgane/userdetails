package com.ing.pact.userdetails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ing.pact.userdetails.clients.UserDetailsServiceClient;

@Controller
public class UserDetailsController {
  @Autowired
  private UserDetailsServiceClient userDetailsServiceClient;

  @GetMapping("/userdetails/{id}")
  public String catalogue(@PathVariable("id") Long id, Model model) {
    model.addAttribute("userDetails", userDetailsServiceClient.getUserDetailsById(id));
    return "details";
  }
}
