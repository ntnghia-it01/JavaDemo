package com.fpoly.java5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fpoly.java5.components.PermissionComponents;

@Configuration
public class PermissionConfig implements WebMvcConfigurer{
  
  @Autowired
  PermissionComponents permissionComponents;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(permissionComponents)
    .addPathPatterns("/admin/*", "/user/*") // Thực thi qua handle interceptor
    .excludePathPatterns("/*"); // Không thực thi qua handle interceptor
  }
}

// /user/cart
// /user
// /user/order
// /user/order?id=1
