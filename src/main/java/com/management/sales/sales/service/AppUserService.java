package com.management.sales.sales.service;

import com.management.sales.sales.model.AppUser;
import com.management.sales.sales.repository.impl.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }


    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        System.out.println("User found with email: " + email);

        return new User(
                appUser.getEmail(),
                appUser.getPassword(),
                Collections.singletonList(appUser.getRole().toGrantedAuthority()));
    }


    public AppUser addUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser updateUser(Long id, AppUser appUser) {
        AppUser existingUser = appUserRepository.findById(id).orElse(null);
        existingUser.setName(appUser.getName());
        existingUser.setEmail(appUser.getEmail());
        existingUser.setPassword(appUser.getPassword());
        existingUser.setRole(appUser.getRole());
        return appUserRepository.save(existingUser);
    }

}
