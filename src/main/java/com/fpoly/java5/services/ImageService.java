package com.fpoly.java5.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ImageService {
  
  public String saveImage(MultipartFile file){

    try {
      Path filePath = Paths.get("images");
      // Tao thu muc neu chua ton tai
      Files.createDirectories(filePath);

      String fileName = String.format("%s.%s", (new Date()).getTime(), file.getContentType().split("/")[1]);

      // Luu file co ten goc vao thu muc static/images
      Files.copy(file.getInputStream(), filePath.resolve(fileName));

      return fileName;

    }catch(Exception e){

    }

    return null; // error
  }
}