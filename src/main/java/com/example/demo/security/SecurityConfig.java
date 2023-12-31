package com.example.demo.security;

import com.example.demo.security.filter.LoginFilter;
import com.example.demo.security.filter.MyAuthorizationManager;
import com.example.demo.security.filter.MyOncePerRequestFilter;
import com.example.demo.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;
import java.util.UUID;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthorizationManager<RequestAuthorizationContext> myAuthorizationManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter myOncePerRequestFilter() {
        return new MyOncePerRequestFilter();
    }
    /**
     * 自定义 remember-me 的实现
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 只需要没有表时设置为 true
        jdbcTokenRepository.setCreateTableOnStartup(false);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    @Bean
    public RememberMeServices rememberMeServices() {
        return new PersistentTokenBasedRememberMeServicesImpl(UUID.randomUUID().toString(), userDetailsService, persistentTokenRepository());
    }

    /**
     * 自定义登录过滤器的配置
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public LoginFilter loginFilter(AuthenticationManager authenticationManager) {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/login");

        // 可以自定义用户名和密码的 key
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");

        loginFilter.setAuthenticationManager(authenticationManager);
        loginFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        loginFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        loginFilter.setRememberMeServices(rememberMeServices());
        return loginFilter;
    }

    /**
     * 鉴权、授权
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 开启登录功能
        http.authorizeHttpRequests((register) ->
            register
                .requestMatchers(securityProperties.getMatchers()).permitAll()
                .requestMatchers(HttpMethod.GET, securityProperties.getMethodGETMatchers()).permitAll()
//                .anyRequest().authenticated()
                .anyRequest().access(myAuthorizationManager)
        )

        // 开启登录功能
        // 指定我们的 loginFilter 添加到过滤器链的位置
        .addFilterAt(loginFilter(http.getSharedObject(AuthenticationManager.class)), UsernamePasswordAuthenticationFilter.class)
        // 指定我们自定义的 Service 实现类
        .userDetailsService(userDetailsService)
        .formLogin(Customizer.withDefaults())

        .rememberMe((remember)-> remember.rememberMeServices(rememberMeServices())
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(10))

        // 开启注销功能
        .logout((logout) ->
            logout.deleteCookies("remove")
                    .invalidateHttpSession(false)
                    .logoutUrl("/custom-logout")
                    .logoutSuccessUrl("/logout-success")
                    .logoutSuccessHandler(new MyLogoutSuccessHandler()))

        //异常的处理
        .exceptionHandling((exception)-> exception
                .accessDeniedHandler(new MyAccessDeniedHandler())
//                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
        )
        // 关闭 csrf 防御
        .anonymous(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }

}
