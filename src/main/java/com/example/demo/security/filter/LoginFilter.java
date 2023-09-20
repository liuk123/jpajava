package com.example.demo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                // 采用 getUsernameParameter() 方式，我们可以自定义 json 数据中用户名和密码的 key
                String username = userInfo.get(getUsernameParameter());
                String password = userInfo.get(getPasswordParameter());

                String rememberValue = userInfo.get(AbstractRememberMeServices.DEFAULT_PARAMETER);
                if (!ObjectUtils.isEmpty(rememberValue)) {
                    request.setAttribute(AbstractRememberMeServices.DEFAULT_PARAMETER, rememberValue);
                }

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
