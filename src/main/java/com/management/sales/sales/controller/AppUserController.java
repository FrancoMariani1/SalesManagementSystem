package com.management.sales.sales.controller;

import com.management.sales.sales.dto.AppUserCreationDto;
import com.management.sales.sales.dto.UpdateAppUserDto;
import com.management.sales.sales.model.AppUser;
import com.management.sales.sales.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "localhost:3000")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return appUserService.getUserById(id).orElse(null);
    }

    @PostMapping
    public AppUser addUser(@RequestBody AppUserCreationDto appUserCreationDto) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserCreationDto.getName());
        appUser.setEmail(appUserCreationDto.getEmail());
        appUser.setPassword(appUserCreationDto.getPassword());
        appUser.setRole(appUserCreationDto.getRole());
        return appUserService.addUser(appUser);
    }

//    @PostMapping
//    public AppUser addUser(@RequestBody AppUser appUser) {
//        return appUserService.addUser(appUser);
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody UpdateAppUserDto updateAppUserDto) {
        AppUser existingUser = appUserService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(updateAppUserDto.getName());
        existingUser.setEmail(updateAppUserDto.getEmail());
        existingUser.setPassword(updateAppUserDto.getPassword());
        existingUser.setRole(updateAppUserDto.getRole());
        return appUserService.updateUser(id, existingUser);
    }

//    @PutMapping("/{id}")
//    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
//        return appUserService.updateUser(id, appUser);
//    }


}
