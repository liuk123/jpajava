package com.example.demo.security.filter;

import com.example.demo.model.User;
import com.example.demo.security.CustomUser;
import com.example.demo.security.MetadataCustomizer;
import com.example.demo.security.SecurityContext;
import com.example.demo.service.AbacService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyOncePerRequestFilter extends OncePerRequestFilter  {
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private AbacService abacService;
    @Autowired
    private List<MetadataCustomizer> metadataCustomizers;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        UserDetails user = toUser();
//        List<String> permissions = securityContext.rbacPermissions(user, abacService.getAll(), metadataCustomizers);
//        List<GrantedAuthority> abacAuthority = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        user.getAuthorities().addAll(abacAuthority);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication instanceof CustomUser customUser){
//            if(isAuthorized(customUser)){
//                List<String> permissions = securityContext.rbacPermissions(customUser.getUser(), abacService.getAll(), metadataCustomizers);
//                List<GrantedAuthority> authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUser.getUser(), null, authorities);
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                filterChain.doFilter(request, response);
//            }
//        }

        CustomUser customUser =(CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<String> permissions = securityContext.rbacPermissions(customUser.getUser(), abacService.getAll(), metadataCustomizers);
        List<GrantedAuthority> authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUser.getUser(), null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
    private boolean isAuthorized(CustomUser customUser){
        return true;
    }
}
