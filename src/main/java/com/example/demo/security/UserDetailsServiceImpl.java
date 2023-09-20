package com.example.demo.security;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userService.getUserByLoginName(username);
        if(userInfo == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        new SimpleGrantedAuthority("12");

        return new CustomUser(userInfo, authorities);
    }
}
