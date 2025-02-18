package com.fpoly.java5.jpas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.java5.entities.Image;

public interface ImageJPA extends JpaRepository<Image, Integer>{
  
  // Lệnh sql xoá ảnh theo product id

  @Query(value = "DELETE FORM images WHERE prod_id=?1", nativeQuery = true)
  public void deleteByProdId(int id);
}
