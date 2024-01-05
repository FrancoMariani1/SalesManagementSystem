//package com.management.sales.Sales.service;
//
//
//import com.management.sales.Sales.repository.impl.AppUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AppUserRepository appUserRepository;
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return appUserRepository.findByEmail(email)
//                .map(appUser -> User.withUsername(appUser.getEmail())
//                        .password(appUser.getPassword())
//                        .roles(appUser.getRole())
//                        .build())
//                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//    }
//
//}



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("admin@admin.com".equals(username)) {
//            return User.withUsername("admin@admin.com")
//                    .password(passwordEncoder.encode("admin"))
//                    .roles("ADMIN")
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//
//
//}

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return appUserRepository.findByEmail(email)
//                .map(appUser -> User.withUsername(appUser.getEmail())
//                        .password(appUser.getPassword())
//                        .roles(appUser.getRole())
//                        .build())
//                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Aquí puedes buscar el usuario en tu base de datos
//        // Por ahora, vamos a crear un usuario estático
//        if ("user".equals(username)) {
//            return User.withUsername("user")
//                    .password(passwordEncoder().encode("password"))
//                    .authorities("ROLE_USER")
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//    }

//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

