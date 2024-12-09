package com.management.sales.sales.config;

import com.management.sales.sales.filters.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Configuración sin estado para JWT
                )
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
//                            .requestMatchers(new AntPathRequestMatcher("/users/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/customers/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/products/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/invoices/**", "GET")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html", "GET")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                            .requestMatchers(new AntPathRequestMatcher("/**", "DELETE")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/**", "POST")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/**", "PUT")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/auth/register")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "/v3/api-docs/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/auth/login", "POST")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/auth/register", "POST")).permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(httpBasic -> httpBasic.disable())  // Desactiva autenticación básica
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);  // Agrega el filtro JWT antes del de autenticación básica

//                .httpBasic(withDefaults());

        return http.build();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("http://localhost:3000"); // Origen específico
        configuration.addAllowedOriginPattern("http://localhost:8080"); // Swagger
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Requestor-Type", "Content-Type"));
        configuration.setAllowCredentials(true); // Necesario para permitir cookies/autenticación

        System.out.println("CORS Configuration:");
        System.out.println("Allowed Origin Patterns: " + configuration.getAllowedOriginPatterns());
        System.out.println("Allowed Methods: " + configuration.getAllowedMethods());
        System.out.println("Allowed Headers: " + configuration.getAllowedHeaders());
        System.out.println("Allow Credentials: " + configuration.getAllowCredentials());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}


//@Bean
//    SecurityFilterChain web(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(withDefaults())
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

//package com.management.sales.Sales.config;
//
//import com.management.sales.Sales.service.AppUserService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//
//import java.util.Arrays;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.builders.RequestMatcherConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.util.AntPathMatcher;
//
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private AppUserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder
//                .userDetailsService(userService)
//                .passwordEncoder(passwordEncoder);
//        return authenticationManagerBuilder.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeRequests(authorizeRequests -> {
//                    authorizeRequests
//                            .requestMatchers(new AntPathRequestMatcher("/users/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/customers/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/products/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/invoices/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/h2-console/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                            .requestMatchers(new AntPathRequestMatcher("/**", "DELETE")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/**", "POST")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/**", "PUT")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "/v3/api-docs/**")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/auth/login", "POST")).permitAll()
//                            .anyRequest().authenticated();
//                })
//                .httpBasic(withDefaults());
//
//        return http.build();
//    }
//
//
//}


//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private AppUserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeRequests(authorizeRequests -> {
//                    authorizeRequests
//                            .requestMatchers(new AntPathRequestMatcher("/users/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/customers/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/products/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/invoices/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/h2-console/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**", "GET")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                            .requestMatchers(new AntPathRequestMatcher("/**", "DELETE")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/**", "POST")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/**", "PUT")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll()
//                            .requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
//                            .anyRequest().authenticated();
//                })
//                .httpBasic(withDefaults());
//
//
//        return http.build();
//    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
////    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return customAuthenticationManagerBuilder(null).build();
//    }
//
//    //    @Bean
////    public UserDetailsManager userDetailsManager() {
////        return new InMemoryUserDetailsManager();
////    }
//    @Bean
//    @Primary
//    public AuthenticationManagerBuilder customAuthenticationManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
//        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
//        builder
//                .userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder);
//
//        return builder;
//
//    }
//
//}

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
//    }













//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private AppUserService userService;
//
////    @Autowired
////    private MyUserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests((authz) -> authz
//                                .requestMatchers(new AntPathRequestMatcher("/users/**", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/customers/**", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/products/**", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/invoices/**", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/h2-console/**", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html", "GET")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**", "GET")).permitAll()
//
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/users/**")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/customers/**")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/products/**")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/invoices/**")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/h2-console/**")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/swagger-ui/**")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/swagger-ui.html")).permitAll()
////                                .dispatcherTypeMatchers(HttpMethod.valueOf("/v3/api-docs/**")).permitAll()
//
////                        .requestMatchers(new MvcRequestMatcher(introspector,"/users/**")).permitAll()
////                        .requestMatchers(new MvcRequestMatcher(introspector,"/h2-console/**")).permitAll()
//                                .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
////                .authenticationManager(authenticationManager());
//        return http.build();
//    }



//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//
//
//    }


//    public AuthenticationManager authenticationManager() {
//        return authentication -> {
//            String email = authentication.getPrincipal() + "";
//            String password = authentication.getCredentials() + "";
//            if (email.equals("admin") && password.equals("admin")) {
//                authentication.setAuthenticated(true);
//            } else {
//                authentication.setAuthenticated(false);
//            }
//            return authentication;
//        };




