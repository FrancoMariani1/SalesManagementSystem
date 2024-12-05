package com.management.sales.sales.service;

import com.management.sales.sales.dto.LoginRequest;
import com.management.sales.sales.dto.RegisterUserDto;
import com.management.sales.sales.model.AppUser;
import com.management.sales.sales.model.AppUserRole;
import com.management.sales.sales.repository.impl.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.management.sales.sales.util.JwtUtil;

import java.util.Collections;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserDetails loadUserByEmail(String email) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(user.getRole().toGrantedAuthority()));
    }

    public String authenticateUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        UserDetails userDetails = new User(user.getEmail(), user.getPassword(), Collections.singletonList(user.getRole().toGrantedAuthority()));
        return jwtUtil.generateToken(userDetails);
    }

    public void registerUser(RegisterUserDto registerUserDto) {
        String email = registerUserDto.getEmail();

        if (appUserRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        AppUser appUser = new AppUser(
                registerUserDto.getName(),
                registerUserDto.getEmail(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                AppUserRole.valueOf(registerUserDto.getRole().toUpperCase())
        );

        appUserRepository.save(appUser);
    }
}