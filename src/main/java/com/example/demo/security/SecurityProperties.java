package com.example.demo.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "demo.security")
@Configuration
@Data
public class SecurityProperties {
    private String loginPage = "http://www.cicode.cn/user/login";
    private String loginProcessingUrl = "/user/login";
    private String logoutUrl = "/user/logout";

    private String[] matchers = new String[]{
            "/user/**",
            "/news/**",
            "/amount/**"
    };
    private String[] MethodGETMatchers = new String[]{
            "/link/**",
    };

}