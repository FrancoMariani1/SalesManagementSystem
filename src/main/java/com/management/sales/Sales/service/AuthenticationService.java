//package com.management.sales.Sales.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Service;
//import com.tu.paquete.security.JwtTokenProvider;
//import com.tu.paquete.dto.LoginRequest;
//
//@Service
//public class AuthenticationService {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider tokenProvider;
//
//    @Autowired
//    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.tokenProvider = tokenProvider;
//    }
//
//    public String login(LoginRequest loginRequest) throws AuthenticationException {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        // Si la autenticación fue exitosa, generamos el token JWT
//        return tokenProvider.createToken(authentication);
//    }
//}
