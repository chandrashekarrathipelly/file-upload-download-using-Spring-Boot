package com.example.fileTransfer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.fileTransfer.Dto.AuthRequest;
import com.example.fileTransfer.Dto.AuthResponse;
import com.example.fileTransfer.Service.UserService;
import com.example.fileTransfer.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    
    @Autowired 
    UserService userService;
    
    @PostMapping("auth/users/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return userService.login(authRequest);
    }

    @PostMapping("auth/users/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    
    
}
