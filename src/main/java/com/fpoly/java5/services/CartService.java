package com.fpoly.java5.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.java5.entities.CartEntity;
import com.fpoly.java5.entities.CartItemEntity;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.CartJPA;
import com.fpoly.java5.jpas.UserJPA;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CartService {

  @Autowired
  HttpServletRequest request;

  @Autowired
  CartJPA cartJPA;

  @Autowired
  UserJPA userJPA;

  private UserEntity getUserEntity(){
    Cookie[] cookies = request.getCookies();
    if(cookies == null){
      return null;
    }
    for(Cookie cookie : cookies){
      if(cookie.getName().equals("user_id")){
        Optional<UserEntity> userOptional = userJPA.findById(Integer.parseInt(cookie.getValue()));
        return userOptional.isPresent() ? userOptional.get() : null;
      }
    }
    return null;
  }

  private CartEntity getCart(){
    try{
      UserEntity userEntity = this.getUserEntity();
      CartEntity cart = userEntity.getCart();
      if(cart != null){
        return cart;
      }

      CartEntity cartEntity = new CartEntity();
      cartEntity.setUser(userEntity);

      return cartJPA.save(cartEntity);
    }catch(Exception e){
      return null;
    }
  }

  public List<CartItemEntity> getCartItem(){
    CartEntity cartEntity = this.getCart();
    return cartEntity != null ? cartEntity.getCartItems() : new ArrayList<>();
  }

  public boolean addToCart(int prodId){
    CartEntity cartEntity = this.getCart();

    // Cách 1:
    // Viết JPA cart item where prod_id and cart_id => thực thi 1 lệnh sql
    // Kiểm tra nếu không có insert => thực hiện kiểm tra 1
    // Nếu có tăng quantity + 1 update

    // Cách 2:
    // Từ cart entity get list item => thực thi 1 lệnh sql
    // for => cart item ==  prod_id => update => ktra không xác định số lần
    // nếu không có insert

    // TH 1: Sản phẩm chưa có trong giỏ hàng
    // TH 2: Sản phẩm đã có trong giỏ hàng

    return true;
  }

  public boolean deleteCartItem(int cartItemId){

    return true;
  }

  public boolean updateQuantityCartItem(int cartItemId, int quantity){

    return true;
  }
}
