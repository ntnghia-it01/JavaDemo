package com.fpoly.java5.components;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PermissionComponents implements HandlerInterceptor{
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // TODO Auto-generated method stub
    // Được gọi trước khi hàm controller được kích hoạt

    // Kiểm tra trong cookie của website
    // Nếu chưa có cookie (Chưa đăng nhập)
    // Chuyển về trang login

    Cookie[] cookies = request.getCookies();
    if(cookies == null){
      response.sendRedirect("/login");
      return false;
    }

    String path = request.getServletPath();
    int role = -1;

    for(Cookie cookie : cookies){
      if(cookie.getName().equals("user_role")){
        role = Integer.parseInt(cookie.getValue());
        break;
      }
    }

    if(role == 0 && path.contains("/admin")){
      return true;
    }else if (role == 1 && path.contains("/user")){
      return true;
    }else {
      response.sendRedirect("/login");
      return false;
    }

    // Có 2 url: /products, /users
    // Cookie role == 0 => /users
    // Cookie role == 1 => /products

    // return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub
    // Được gọi khi hàm trong controller bắt đầu thực thi
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    // TODO Auto-generated method stub
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
