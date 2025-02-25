package com.fpoly.java5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.java5.beans.ProductBean;
import com.fpoly.java5.entities.Category;
import com.fpoly.java5.entities.Product;
import com.fpoly.java5.jpas.CategoryJPA;
import com.fpoly.java5.jpas.ProductJPA;
import com.fpoly.java5.services.ProductService;

@Controller
public class ProductController {

  @Autowired
  CategoryJPA categoryJPA;

  @Autowired
  ProductService productService;

  @Autowired
  ProductJPA productJPA;

  @GetMapping("/user/products")
  public String productsLayout(Model model){
    List<Product> products = productJPA.findAll();
    model.addAttribute("products", products);
    return "/products.html";
  } 

  @GetMapping("/product-form")
  public String productFormLayout(){
    return "/product-form.html";
  } 

  @PostMapping("/product-form")
  public String handleProductFormLayout(ProductBean productBean){
    // validate error
    System.out.println("desc === " + productBean.getDesc());
    productService.saveProduct(productBean);
    return "/product-form.html";
  } 

  // @ModelAttribute("categories")
  // public List<Category> getCategories(){
  //   return categoryJPA.findAll();
  // }
}
