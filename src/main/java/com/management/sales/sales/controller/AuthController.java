package com.management.sales.sales.controller;

import com.management.sales.sales.dto.AuthenticationResponse;
import com.management.sales.sales.dto.LoginRequest;
import com.management.sales.sales.model.AppUser;
import com.management.sales.sales.service.AuthService;
import com.management.sales.sales.service.AppUserService;
import com.management.sales.sales.security.JwtUtilService;
import com.management.sales.sales.dto.RegisterUserDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final JwtUtilService jwtUtilService;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager, AppUserService appUserService, JwtUtilService jwtUtilService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.jwtUtilService = jwtUtilService;
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(
                    new AuthenticationResponse(null, null, "Incorrect username or password")
            );
        }

        final UserDetails userDetails = appUserService
                .loadUserByUsername(loginRequest.getEmail());

        final Optional<AppUser> optionalAppUser = appUserService.getUserByEmail(loginRequest.getEmail());
        if (!optionalAppUser.isPresent()) {
            throw new EntityNotFoundException("User not found with email: " + loginRequest.getEmail());
        }
        final AppUser appUser = optionalAppUser.get();

        final String role = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        final String jwt = jwtUtilService.generateToken(userDetails, appUser.getId(), role);

//        // Convertir roles a una lista de strings
//        final List<String> roles = userDetails.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();

        // Crear respuesta
        AuthenticationResponse response = new AuthenticationResponse(jwt, Collections.singletonList(role), "Login successful");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto, HttpServletRequest request) {
        try {
            // Extraer el email del usuario autenticado desde el token
            String currentUserEmail = jwtUtilService.extractUsername(jwtUtilService.getToken(request));

            // Llamar al servicio para registrar el usuario, pasando el email del usuario actual
            authService.registerUser(registerUserDto, currentUserEmail);

            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalStateException e) {
            // Manejar errores relacionados con la lógica del registro
            return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}



//package com.management.sales.Sales.controller;
//
//import com.management.sales.Sales.dto.LoginRequest;
//import com.management.sales.Sales.model.AppUser;
//import com.management.sales.Sales.service.AuthService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final AuthService authService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
//        this.authService = authService;
//        this.authenticationManager = authenticationManager;
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        AppUser authenticatedUser = authService.authenticateUser(loginRequest);
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                authenticatedUser, null, authenticatedUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return ResponseEntity.ok().build();
//
//    }
//
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser) {
//        AppUser registeredUser = authService.registerUser(appUser);
//
//        return ResponseEntity.ok().build();
//    }
//
//}







//package com.management.sales.Sales.controller;
//
//import com.management.sales.Sales.dto.LoginRequest;
//import com.management.sales.Sales.dto.LoginResponse;
//import com.management.sales.Sales.service.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.naming.AuthenticationException;
//
//public class AuthController {
//
//    private final AuthenticationService authenticationService;
//
//    @Autowired
//    public AuthController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            String token = authenticationService.login(loginRequest);
//            return ResponseEntity.ok(new LoginResponse(token));
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en la autenticación");
//        }
//    }
//}

