package com.fpoly.java5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.java5.beans.UserBean;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.UserJPA;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserJPA userJPA;

    @GetMapping("/users")
    public String users(Model model){

        List<UserEntity> userEntities = userJPA.findAll();

        model.addAttribute("users", userEntities);

        return "/users.html";
    }

    @GetMapping("/add-user")
    public String addUserForm(){

        return "/user-form.html";
    }

    @PostMapping("/add-user")
    public String handleAddUser(
        @Valid @ModelAttribute("user") UserBean userBean,
        Errors errors,
        Model model
    ){
        // Thực hiện lưu ảnh và lưu db

        if(errors.hasErrors() || userBean.isAvatarError() != null){
            // Kiểm tra nếu bean có lỗi thì chuyển về trang user-form.html
            // Dùng th:erros hiển thị lỗi ở input text
            // File => addAttr fileError
            return "/user-form.html";
        }

        // handle

        return "";
    }
}
