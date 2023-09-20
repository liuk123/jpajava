package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class PersistentTokenBasedRememberMeServicesImpl extends PersistentTokenBasedRememberMeServices {
    public PersistentTokenBasedRememberMeServicesImpl(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        String paramValue = request.getAttribute(parameter).toString();
        if (paramValue != null) {
            return paramValue.equalsIgnoreCase("true");
//            return paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on")
//                    || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1");
        }
        return false;
    }
}
