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
