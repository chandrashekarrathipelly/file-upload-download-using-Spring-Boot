package com.example.fileTransfer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fileTransfer.entity.User;


public interface UserRepository extends JpaRepository<User,Long> {
   
    @Query("select u from User u where u.username=:userName")
    <Optional>User  findByUsername(String userName);
}
