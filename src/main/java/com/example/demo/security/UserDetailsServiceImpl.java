package com.example.demo.security;

import com.example.demo.db.User;
import com.example.demo.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService, MetadataCustomizer {
    final UserService userService;
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(user.getStatus() == 0){
            throw new RuntimeException("用户已被禁用");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : userInfo.getRoleList()) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
//        }
        return new CustomUser(user, authorities);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        CustomUser customUser = (CustomUser) user;
        User u = userService.getUserByUsername(user.getUsername());
        u.setPassword(newPassword);
        userService.save(u);
//        User u = customUser.getUser();
//        u.setPassword(newPassword);
        customUser.setUser(u);
        return customUser;
    }

    @Override
    public void customize(User user){
        user.getMetadata().put("ip", "192.168.0.1");
    }
}
