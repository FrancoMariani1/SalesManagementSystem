//package com.management.sales.Sales.controller;
//
//import com.management.sales.Sales.util.JwtRequest;
//import com.management.sales.Sales.util.JwtResponse;
//import com.management.sales.Sales.util.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import javax.naming.AuthenticationException;
//import java.util.Objects;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "localhost:3000")
//public class AuthController {
//
//    private AuthenticationManager authenticationManager;
//
//    private JwtTokenUtil jwtTokenUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws AuthenticationException {
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        final UserDetails userDetails = null;
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//
//    private void authenticate(String username, String password) throws AuthenticationException {
//        Objects.requireNonNull(username);
//        Objects.requireNonNull(password);
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new AuthenticationException("USER_DISABLED");
//        } catch (BadCredentialsException e) {
//            throw new AuthenticationException("INVALID_CREDENTIALS");
//        }
//    }
//
//
//
//
//}

