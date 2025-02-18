package com.fpoly.java5.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.java5.beans.ProductBean;
import com.fpoly.java5.entities.Category;
import com.fpoly.java5.entities.Image;
import com.fpoly.java5.entities.Product;
import com.fpoly.java5.jpas.CategoryJPA;
import com.fpoly.java5.jpas.ImageJPA;
import com.fpoly.java5.jpas.ProductJPA;

@Service
public class ProductService {
  
  @Autowired
  ProductJPA productJPA;

  @Autowired
  ImageJPA imageJPA;

  @Autowired
  CategoryJPA categoryJPA;

  @Autowired
  ImageService imageService;

  public boolean saveProduct(ProductBean productBean){
    try{
      // Luu san pham vao db
      // Luu anh vao project va luu ten anh vao db

      Product productEntity = new Product();
      productEntity.setName(productBean.getName());
      productEntity.setDesc(productBean.getDesc());
      productEntity.setPrice(productBean.getPrice());
      productEntity.setQuantity(productBean.getQuantity());
      productEntity.setActive(true);

      Optional<Category> cOptional = categoryJPA.findById(productBean.getCat_id());
      if(cOptional.isPresent()){
        productEntity.setCategory(cOptional.get());
      }

      Product productEntitySaved = productJPA.save(productEntity);

      for(MultipartFile file : productBean.getImages()){
        String fileName = imageService.saveImage(file);
        Image imageEntity = new Image();
        imageEntity.setImage(fileName);
        imageEntity.setProduct(productEntitySaved);

        imageJPA.save(imageEntity);
      }


    }catch(Exception e){
      
      return false;
    }

    return true;
  }

  public boolean deleteProduct(int id){
    try{
      imageJPA.deleteByProdId(id);
      productJPA.deleteById(id);
    }catch(Exception e){
      return false;
    }
    return true;
  }
}
