package com.example.demo.security.filter;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;


public class MyAuthorizationManager <T> implements AuthorizationManager<T> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> supplier, T object) {
        boolean isMatch = false;
        RequestAuthorizationContext requestAuthorizationContext= (RequestAuthorizationContext) object;
        String requestURI = requestAuthorizationContext.getRequest().getRequestURI();
//        List<MenuWithRoleVO> menuWithRole = menuService.getMenuWithRole();
//        Authentication authentication = supplier.get();

//        boolean isAnonymous = authentication != null && !this.trustResolver.isAnonymous(authentication)
//                && authentication.isAuthenticated();

//        if(!isAnonymous) {
//            return new AuthorizationDecision(false);
//        }

//        String servletPath = httpServletRequest.getServletPath();
        // TODO: 判断当前用户是否拥有访问servletPath的权限
        boolean granted = true;

        return new AuthorizationDecision(granted);
    }
}
