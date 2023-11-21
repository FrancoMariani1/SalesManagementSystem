package com.management.sales.Sales.config;

import com.management.sales.Sales.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private AppUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Bean
//    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
//        return new HandlerMappingIntrospector();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .dispatcherTypeMatchers(HttpMethod.valueOf("/users/**")).permitAll()
                        .dispatcherTypeMatchers(HttpMethod.valueOf("/h2-console/**")).permitAll()
                                .dispatcherTypeMatchers(HttpMethod.valueOf("/swagger-ui/**")).permitAll()
        .dispatcherTypeMatchers(HttpMethod.valueOf("/swagger-ui.html")).permitAll()
        .dispatcherTypeMatchers(HttpMethod.valueOf("/v3/api-docs/**")).permitAll()

//                        .requestMatchers(new MvcRequestMatcher(introspector,"/users/**")).permitAll()
//                        .requestMatchers(new MvcRequestMatcher(introspector,"/h2-console/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .authenticationManager(authenticationManager());
        return http.build();
    }


    public AuthenticationManager authenticationManager() {
        return authentication -> {
            String email = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";
            if (email.equals("admin") && password.equals("admin")) {
                authentication.setAuthenticated(true);
            } else {
                authentication.setAuthenticated(false);
            }
            return authentication;
        };
    }


}
