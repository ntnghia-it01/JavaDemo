package com.fpoly.java5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.java5.beans.UserBean;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.UserJPA;
import com.fpoly.java5.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserJPA userJPA;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String users(Model model){

        List<UserEntity> userEntities = userJPA.findAll();

        model.addAttribute("users", userEntities);

        return "/users.html";
    }

    @GetMapping("/add-user")
    // Nhận giá trị id được truyền qua
    public String addUserForm(
        @RequestParam("id") Optional<Integer> id,
        Model model
        // @RequestParam(name = "id", defaultValue = "0") int id
        // @RequestParam(name = "id", required = false) int id
    ){
        // Nếu có truyền id qua
        // Đổi tên và button add user => update user
        // Hiển thị thông tin user từ db thông qua id ở các ô input
        // Về nhà giải quyết

        if(id.isPresent()){
            // find by id user db
            // attr qua html
        }

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

            System.out.println("Error bean or image " + userBean.isAvatarError());
            return "/user-form.html";
        }

        // handle

        String result = userService.insertUser(userBean);

        if(result == null){
            return "redirect:/users";
        }

        System.out.println("Error service");
        // Dùng model để truyền lỗi qua
        model.addAttribute("error", result);

        return "/user-form.html";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("id") int id){

        boolean delete = userService.deleteUser(id);

        if(!delete){
            // Gui attr qua redirect hien thi loi
        }

        return "redirect:/users";
    }
}
