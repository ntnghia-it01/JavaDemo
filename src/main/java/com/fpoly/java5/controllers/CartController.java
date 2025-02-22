package com.fpoly.java5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
  
  @GetMapping("/user/cart")
  public String cartLayout(){

    return "/cart.html";
  }
}
