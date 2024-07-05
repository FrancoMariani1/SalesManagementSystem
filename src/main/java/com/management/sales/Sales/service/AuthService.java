package com.management.sales.Sales.service;

import com.management.sales.Sales.config.PasswordEncoderConfig;
import com.management.sales.Sales.dto.LoginRequest;
import com.management.sales.Sales.model.AppUser;
import com.management.sales.Sales.repository.impl.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;

    private PasswordEncoderConfig passwordEncoder;

    @Autowired
    public AuthService(AppUserRepository appUserRepository, PasswordEncoderConfig passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser authenticateUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        if (passwordEncoder.PasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return user;

    }

    public AppUser registerUser(AppUser appUser) {
        String email = appUser.getEmail();
        if (appUserRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        appUser.setPassword(passwordEncoder.PasswordEncoder().encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

}
