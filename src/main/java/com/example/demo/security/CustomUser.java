package com.example.demo.security;

import com.example.demo.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

@Getter
public class CustomUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 4547932757380981967L;

    private User user;
    private String token;
    public CustomUser(User user, Collection<? extends GrantedAuthority> authorities){
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public void setUser(User user){
        user.setPassword(null);
        this.user = user;
    }

    public void setToken(String token){
        this.token = token;
    }
}
