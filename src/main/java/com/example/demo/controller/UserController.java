package com.example.demo.controller;

import com.example.demo.db.model.User;
import com.example.demo.security.CustomUser;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path="/currentUser")
    public User getCurrentUser(Authentication authentication){
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return customUser.getUser();
    }

    @GetMapping(path="/currentUser1")
    public User getCurrentUser1(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return customUser.getUser();
    }
}
