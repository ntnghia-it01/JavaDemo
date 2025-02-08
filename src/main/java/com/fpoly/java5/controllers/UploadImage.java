package com.fpoly.java5.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadImage {

  @GetMapping("/upload-image")
  public String uploadImage() {
    // get categories cua image
    // Gui qua model
    return "/upload-image.html";
  }

  @ModelAttribute("categories")
  public List<String> getCategories() {
    return List.of("Nature", "Animal", "People", "Food", "Technology", "Sport", "Other");
  }

  @PostMapping("/upload-image")
  public String handleUploadImage(@RequestPart("file") MultipartFile file) {

  // get categories cua image
    // Gui qua model

    Path filePath = Paths.get("images");

    try {
      // Tao thu muc neu chua ton tai
      Files.createDirectories(filePath);

      String fileName = String.format("%s.%s", (new Date()).getTime(), file.getContentType().split("/")[1]);

      // Luu file co ten goc vao thu muc static/images
      Files.copy(file.getInputStream(), filePath.resolve(fileName));

      // Thread.sleep(10);

      // redirect => /image-preview?name=ten-file

      return "foward:/image-preview?name=" + fileName;

    } catch (Exception e) {
      e.printStackTrace();
    }

    // for(MultipartFile file : files){
    // Path filePath = Paths.get("src/main/resources/static/images");

    // try {
    // // Tao thu muc neu chua ton tai
    // Files.createDirectories(filePath);

    // String fileName = String.format("%s.%s", (new Date()).getTime(),
    // file.getContentType().split("/")[1]);

    // // Luu file co ten goc vao thu muc static/images
    // Files.copy(file.getInputStream(), filePath.resolve(fileName));

    // Thread.sleep(10);

    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // System.out.println("File name: " + file.getOriginalFilename());
    // System.out.println("File size: " + file.getSize());
    // System.out.println("File type: " + file.getContentType());

    // image/png, image/webp, image/jpeg, image/gif

    // png, webp, jpg, jpeg, gif

    // Truy cap vao thu muc static/images

    return "/upload-image.html";
  }

  // return "redirect:/image-preview?name=" + fileName;

  @GetMapping("/image-preview")
  public String imagePreview(@RequestParam("name") String name, Model model) {
    model.addAttribute("url", "/images/" + name);
    return "/image-preview.html";
  }
}
