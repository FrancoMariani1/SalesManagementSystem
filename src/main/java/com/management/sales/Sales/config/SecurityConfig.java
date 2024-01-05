package com.management.sales.Sales.config;

import com.management.sales.Sales.service.AppUserService;
//import com.management.sales.Sales.service.MyUserDetailsService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

//    @Value("${spring.profiles.active}")
//    private String activeProfile;

    @Autowired
    private AppUserService userService;


//    @Autowired
//    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("admin@hot.com")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((authz) -> authz
                                .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/customers/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/products/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/invoices/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/index.html#/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/index.html/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()

                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
//
        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("password");
    }

//    private boolean isDevelopmentEnvironment() {
//        return "development".equals(activeProfile);
//    }
}

//@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        if (isDevelopmentEnvironment()) {
//            // Configuración especial para el entorno de desarrollo
//            http
//                    .authorizeRequests(authorize -> authorize
//                            .requestMatchers(
//                                    new AntPathRequestMatcher("/swagger-ui/**"),
//                                    new AntPathRequestMatcher("/swagger-ui.html"),
//                                    new AntPathRequestMatcher("/v3/api-docs/**")
//                            ).permitAll()
//
//                    )
//                    .httpBasic(withDefaults()); // Deshabilitar autenticación básica en desarrollo
//        } else {
//            // Configuración normal para otros entornos (producción, etc.)
//            http
//                    .authorizeRequests(authorize -> authorize
//                                    .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
//                                    .requestMatchers(new AntPathRequestMatcher("/customers/**")).permitAll()
//                                    .requestMatchers(new AntPathRequestMatcher("/products/**")).permitAll()
//                                    .requestMatchers(new AntPathRequestMatcher("/invoices/**")).permitAll()
//                                    .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//
//                            // Otras configuraciones de requestMatchers aquí
//                    )
//                    .httpBasic(withDefaults());
//        }
//
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests((authz) -> authz
//                                .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/customers/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/products/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/invoices/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/index.html#/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
//
////                                .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
////
//        return http.build();
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




