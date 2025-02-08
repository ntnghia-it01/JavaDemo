package com.fpoly.java5.entities;

import org.hibernate.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "addresses")
public class AddressEnity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;
  @Column(name = "name", length = 255, nullable = false, columnDefinition = "nvarchar")
  private String name;
  @Column(name = "address", nullable = false, columnDefinition = "nvarchar(max)")
  private String address;
  @Column(name = "phone", length = 15, nullable = false)
  private String phone;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  UserEntity userEntity;
}


// CREATE TABLE [dbo].[addresses] (
//     [id]      INT            IDENTITY (1, 1) NOT NULL,
//     [user_id] INT            NOT NULL,
//     [name]    NVARCHAR (255) NOT NULL,
//     [address] NVARCHAR (MAX) NOT NULL,
//     [phone]   VARCHAR (15)   NOT NULL,
//     CONSTRAINT [PK_addresses] PRIMARY KEY CLUSTERED ([id] ASC),
//     CONSTRAINT [FK_addresses_users] FOREIGN KEY ([user_id]) REFERENCES [dbo].[users] ([id])
// );

