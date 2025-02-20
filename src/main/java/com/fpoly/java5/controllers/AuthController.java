package com.fpoly.java5.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.java5.beans.LoginBean;
import com.fpoly.java5.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {


  @Autowired
  HttpServletResponse response;

  @Autowired
  UserService userService;

  @GetMapping("/login")
  public String login() {
    return "/login.html";
  }

  @PostMapping("/login")
  public String hanleLogin(
      LoginBean loginBean,
      Model model) {
    model.addAttribute("username", loginBean.getUsername());
    model.addAttribute("password", loginBean.getPassword());

    // Kiểm tra username và password
    // Với điều kiện username phải khác rỗng "      " => rỗng
    // Và password phải có ít nhất 6 ký tự
    // Thông báo lỗi ở trang login.html

    boolean hasError = false;

    if(loginBean.getUsername().isBlank()){
      model.addAttribute("errorUsername", "Username is required");
      hasError = true;
    }

    if(loginBean.getPassword().length() < 6 || loginBean.getPassword().isBlank()){
      model.addAttribute("errorPassword", "Password is required");
      hasError = true;
    }

    if(hasError){
      return "/login.html";
    }

    boolean checkLogin = userService.checkLogin(loginBean.getUsername(), loginBean.getPassword());

    // if(){
    //   return "redirect:/";
    // }

    // if(loginBean.getUsername().equals("user") && loginBean.getPassword().equals("user")){
    //   return "redirect:/user";
    // }

    return "/login.html";



    // Nếu không có lỗi và username = "admin" và password = "admin"
    // Chuyển trang qua đường dẫn /
    // Nếu username = "user" và password = "user"
    // Chuyển trang qua đường dẫn /user
  }

}
// Khi ấn nút đăng nhập
// Form này sẽ gọi qua đường dẫn /login
// Với phương thức là POST
// Lấy những dữ liệu đã nhận được từ POST
// Hiển thị lại ở ở form input