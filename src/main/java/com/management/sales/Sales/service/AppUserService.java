package com.management.sales.Sales.service;

import com.management.sales.Sales.model.AppUser;
import com.management.sales.Sales.repository.impl.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
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
