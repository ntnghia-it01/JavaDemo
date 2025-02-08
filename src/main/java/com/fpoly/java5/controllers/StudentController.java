package com.fpoly.java5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.java5.beans.StudentBean;
import com.fpoly.java5.entities.MajorEntity;

import jakarta.validation.Valid;

@Controller
public class StudentController {
  
  // @GetMapping("/student-info")
  // public String studentInfo(Model model) {

  //   StudentBean studentBean = new StudentBean();

  //   studentBean.setCode("PC123456");
  //   studentBean.setName("Nguyen Van A");
  //   studentBean.setClassName("SD19303");
  //   studentBean.setMark(4);

  //   // Tao 1 danh sach sinh vien
  //   // Gui qua model
  //   // O html hien thi bang thong tin SV giong nhu bai truoc

  //   model.addAttribute("student", studentBean);


  //   List<StudentBean> students = new ArrayList<>();
  //   students.add(new StudentBean("PC123456", "Nguyen Van A", "SD19303", 4));
  //   students.add(new StudentBean("PC123457", "Nguyen Van B", "SD19303", 8));
  //   students.add(new StudentBean("PC123458", "Nguyen Van C", "SD19303", 2));
  //   students.add(new StudentBean("PC123459", "Nguyen Van D", "SD19303", 9.5));

  //   model.addAttribute("students", students);

  //   return "/student-info.html";
  // }


  @GetMapping("/add-student")
  public String addStudent(Model model) {

    // Danh sach nganh hoc
    // Gui du lieu qua html
    // Hien thi danh sach nganh hoc trong dropdown

    model.addAttribute("student", new StudentBean());

    return "/add-student.html";
  }

  @PostMapping("/add-student")
  public String handleAddStudent(@Valid @ModelAttribute("student") StudentBean studentBean, BindingResult bindingResult, Model model) {

    // model.addAttribute("student", studentBean);

    // errors.hasErrors() == true => co loi
    // if(bindingResult.hasErrors()) {
    //   System.out.println(bindingResult.getFieldError("name").getDefaultMessage());
    // }

    return "/add-student.html";
  }

  @ModelAttribute("majors")
  public List<MajorEntity> getMajors() {
    List<MajorEntity> majors = new ArrayList<>();
    majors.add(new MajorEntity(1, "PTPM (Java)"));
    majors.add(new MajorEntity(2, "PTPM (C#)"));
    majors.add(new MajorEntity(3, "Game"));
    majors.add(new MajorEntity(4, "TKTW (FE)"));
    majors.add(new MajorEntity(5, "TKTW (BE)"));

    return majors;
  }
}
