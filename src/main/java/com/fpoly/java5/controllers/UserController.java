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

        UserBean userBean = new UserBean();
        if(id.isPresent()){
            // find by id user db
            Optional<UserEntity> userOptional = userJPA.findById(id.get());

            // convert entity to bean
            // Kiểm tra có userEntity không
            // Set giá trị từ entity qua bean (userBean)

            if(userOptional.isPresent()){
                userBean.setId(id);
                userBean.setUsername(userOptional.get().getUsername());
                userBean.setPassword(userOptional.get().getPassword());
                userBean.setName(userOptional.get().getName());
                userBean.setEmail(userOptional.get().getEmail());
            }
        }
        model.addAttribute("user", userBean);
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

        String result;

        if(userBean.getId().isPresent()){
            result = userService.updateUser(userBean);
        }else{
            result = userService.insertUser(userBean);
        }

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
