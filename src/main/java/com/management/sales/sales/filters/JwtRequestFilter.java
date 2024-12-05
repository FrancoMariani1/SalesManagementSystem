package com.management.sales.sales.filters;

import com.management.sales.sales.service.AppUserService;
import com.management.sales.sales.security.JwtUtilService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AppUserService userService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/auth/login")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // Obtiene el token JWT del encabezado "Authorization"
            String token = jwtUtilService.getToken(request);

            // Si el token es válido, extrae el nombre de usuario y autentica el usuario
            if (token != null && jwtUtilService.validateToken(token)) {
                String email = jwtUtilService.extractUsername(token);

                // Carga los detalles del usuario a partir del nombre de usuario extraído del token
                UserDetails userDetails = userService.loadUserByUsername(email);

                if (userDetails != null) {
                    // Crea la autenticación con el UserDetails completo
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Establece la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            // Manejo de excepciones en caso de errores en la autenticación JWT
            logger.error("Error al procesar el token JWT", e);
        }

        // Continua con el siguiente filtro en la cadena
        chain.doFilter(request, response);
    }
}




//package com.management.sales.Sales.filters;
//
//import com.management.sales.Sales.service.AppUserService;
//import com.management.sales.Sales.security.JwtUtilService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import java.io.IOException;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private AppUserService userService;
//
//    @Autowired
//    private JwtUtilService jwtUtilService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        String token = jwtUtilService.getToken(request);
//
//        if (token != null && jwtUtilService.validateToken(token))
//        {
//            String email = jwtUtilService.extractUsername(token);
//
//            UserDetails userDetails = userService.loadUserByUsername(email);
//            if (userDetails != null) {
//                UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            }
//        }
//        chain.doFilter(request,response);
//
//    }
//
//}
