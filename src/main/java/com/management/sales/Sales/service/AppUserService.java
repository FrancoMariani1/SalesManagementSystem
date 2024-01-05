package com.management.sales.Sales.service;

import com.management.sales.Sales.config.PasswordEncoderConfig;
import com.management.sales.Sales.model.AppUser;
import com.management.sales.Sales.repository.impl.AppUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Integer id) {
        return appUserRepository.findById(id);
    }

    public AppUser addUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public void deleteUser(Integer id) {
        appUserRepository.deleteById(id);
    }

    public AppUser updateUser(Integer id, AppUser appUser) {
        AppUser existingUser = appUserRepository.findById(id).orElse(null);
        existingUser.setName(appUser.getName());
        existingUser.setEmail(appUser.getEmail());
        existingUser.setPassword(appUser.getPassword());
        existingUser.setRole(appUser.getRole());
        return appUserRepository.save(existingUser);
    }

//    public void createAdminUser(){
//        String email = "admin@admin.com";
//        String plainPassword = "admin";
//
//        Optional<AppUser> existingAdmin = appUserRepository.findByEmail(email);
//        if (!existingAdmin.isPresent()) {
//            AppUser admin = new AppUser();
//            admin.setEmail(email);
//            admin.setPassword(passwordEncoder.encode(plainPassword));
//            admin.setRole("ADMIN");
//
//            appUserRepository.save(admin);
//
//
//        }
//
//
//    }
//
//    @PostConstruct
//    public void init(){
//        createAdminUser();
//    }

}
