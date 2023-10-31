package com.management.sales.Sales.service;

import com.management.sales.Sales.model.AppUser;
import com.management.sales.Sales.repository.impl.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
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

}
