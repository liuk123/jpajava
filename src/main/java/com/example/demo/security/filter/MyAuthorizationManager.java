package com.example.demo.security.filter;

import com.example.demo.db.model.Menu;
import com.example.demo.db.model.Permission;
import com.example.demo.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Component
public class MyAuthorizationManager <T> implements AuthorizationManager<T> {

    @Autowired
    public MenuService menuService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> supplier, T object) {

        boolean isMatch = false;
        HttpServletRequest httpServletRequest = ((RequestAuthorizationContext) object).getRequest();
        List<Menu> menuWithPermissions = menuService.getMenuWithPermission();
        Authentication authentication = supplier.get();
        System.out.println(httpServletRequest.getRequestURI());
        // menu里的权限配置和用户的权限做对比
        for(Menu menu : menuWithPermissions){
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(menu.getUrl());
            if(matcher.matches(httpServletRequest)){
                isMatch=true;
                List<Permission> permissions = menu.getPermissions();
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for(GrantedAuthority authority : authorities){
                    for(Permission permission : permissions){
                        if(authority.getAuthority().equals(permission.getPermission())){
                            return new AuthorizationDecision(true);
                        }
                    }
                }

            }
        }
        if(!isMatch){
            //说明请求的 URL 地址和数据库的地址没有匹配上，对于这种请求，统一只要登录就能访问
            if(authentication instanceof AnonymousAuthenticationToken){
                return new AuthorizationDecision(false);
            }else{
                //说明用户已经认证了
                return new AuthorizationDecision(true);
            }
        }
        return new AuthorizationDecision(false);
    }

}
