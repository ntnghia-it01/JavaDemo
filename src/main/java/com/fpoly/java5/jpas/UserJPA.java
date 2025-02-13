package com.fpoly.java5.jpas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.java5.entities.UserEntity;

public interface UserJPA extends JpaRepository<UserEntity, Integer>{
  // Select find by username
  // public Optional<UserEntity> findByUsername(String username);

  @Query(value = "SELECT * FROM users WHERE username=?1 OR email=?2", nativeQuery = true)
  public List<UserEntity> findByUsernameOrEmail(String username, String email);

  // SELECT * FROM users 
  // WHERE (username='admin' OR email='admin@gmail.com') 
  // AND id != 2

  @Query(value =  "SELECT * FROM users WHERE (username=?1 OR email=?2) AND id != ?3", nativeQuery = true)
  public List<UserEntity> findByUsernameOrEmailAndId(String username, String email, int id);
}
