package com.example.demo.base.dto;

import com.example.demo.base.Gender;
import com.example.demo.db.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;

    private String name;
    private String email;
    private String phone;

    private Gender gender;
    private Date birth;

    private String username;
    private String password;
    private Integer status;

    public User toUser(){
        return User.builder()
                .id(this.getId())
                .name(this.getName())
                .email(this.getEmail())
                .phone(this.getPhone())
                .birth(this.getBirth())
                .username(this.getUsername())
                .gender(this.getGender())
                .status(this.getStatus())
                .password(this.getPassword())
                .build();
    }
}
