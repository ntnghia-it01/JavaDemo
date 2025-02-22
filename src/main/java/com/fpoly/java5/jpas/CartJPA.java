package com.fpoly.java5.jpas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.java5.entities.CartEntity;

public interface CartJPA extends JpaRepository<CartEntity, Integer>{

  // @Query(value = "SELECT * FROM carts WHERE user_id=?1", nativeQuery = true)
  // public Optional<CartEntity> findByUserId(int id);
} 
