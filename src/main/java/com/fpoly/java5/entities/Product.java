package com.fpoly.java5.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "name", nullable = false, columnDefinition = "nvarchar(max)")
  private String name;

  @Column(name = "description", nullable = false, columnDefinition = "nvarchar(max)")
  private String desc;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cat_id")
  private Category category;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  List<Image> images;
}