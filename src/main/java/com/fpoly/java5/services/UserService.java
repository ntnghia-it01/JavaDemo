package com.fpoly.java5.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.java5.beans.UserBean;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.UserJPA;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {
  
  @Autowired
  UserJPA userJPA;

  @Autowired
  ImageService imageService;

  @Autowired
  HttpServletResponse response;

  public String insertUser(UserBean userBean){
    try{

      // username hoặc email tồn tại

      List<UserEntity> users = userJPA.findByUsernameOrEmail(userBean.getUsername(), userBean.getEmail());

      if(users.size() > 0){
        return "Username hoặc email đã tồn tại";
      }

      String fileName = imageService.saveImage(userBean.getAvatar());

      if(fileName == null){
        return "imgae err";
      }

      // pass error => save image => image name


      UserEntity userEntity = new UserEntity();
      userEntity.setUsername(userBean.getUsername());
      userEntity.setPassword(userBean.getPassword());
      userEntity.setName(userBean.getName());
      userEntity.setEmail(userBean.getEmail());
      userEntity.setActive(true);
      userEntity.setRole(1);
      userEntity.setAvatar(fileName);

      userJPA.save(userEntity);

    }catch(Exception e){
      return "Có lỗi khi thêm user";
    }

    return null;
  }

  public boolean deleteUser(int id){
    try{
      Optional<UserEntity> user = userJPA.findById(id);
      if(!user.isPresent()){
        return false; 
      }

      userJPA.delete(user.get());
      
    }catch(Exception e){
      return false;
    }

    return true;
  }

  public String updateUser(UserBean userBean){
    try{

      List<UserEntity> users = userJPA.findByUsernameOrEmailAndId(
        userBean.getUsername(),
        userBean.getEmail(),
        userBean.getId().get()
      );

      if(users.size() > 0){
        return "Username hoặc email đã tồn tại";
      }

      String fileName = imageService.saveImage(userBean.getAvatar());

      if(fileName == null){
        return "imgae err";
      }

      UserEntity userEntity = new UserEntity();
      userEntity.setId(userBean.getId().get());
      userEntity.setUsername(userBean.getUsername());
      userEntity.setPassword(userBean.getPassword());
      userEntity.setName(userBean.getName());
      userEntity.setEmail(userBean.getEmail());
      userEntity.setActive(true);
      userEntity.setRole(1);
      userEntity.setAvatar(fileName);

      userJPA.save(userEntity);

    }catch(Exception e){
      return "Có lỗi khi cập nhật user";
    }

    return null;
  }

  // Xây dựng hàm đăng nhập 
  // Sau khi đăng nhập thành công
  // Lưu user id và role vào cookie

  public boolean checkLogin(String username, String password){
    try{
      // WHERE username='abc' AND password='abc'
      // WHERE username='abc'

      Optional<UserEntity> userOptional = userJPA.findByUsername(username);

      if(!userOptional.isPresent()){
        return false;
      }

      if(!userOptional.get().getPassword().equals(password)){
        return false;
      }

      Cookie cookieUserId = new Cookie("user_id", String.valueOf(userOptional.get().getId()));
      cookieUserId.setMaxAge(60 * 60 * 10);
      Cookie cookieUserRole = new Cookie("user_role", String.valueOf(userOptional.get().getRole()));
      cookieUserRole.setMaxAge(60 * 60 * 10);

      response.addCookie(cookieUserId);
      response.addCookie(cookieUserRole);

    }catch(Exception e){
      return false;
    }
    return true;
  }
}
