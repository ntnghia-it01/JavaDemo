package com.fpoly.java5.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.java5.entities.Product;

public interface ProductJPA extends JpaRepository<Product, Integer>{

}
