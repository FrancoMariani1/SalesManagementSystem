package com.management.sales.sales.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;

@Service
public class JwtUtilService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token-validity}")
    private long tokenValidity;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Genera un token JWT para un usuario específico
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    // Extrae el nombre de usuario del token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Verifica si el token JWT es válido y no ha expirado
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // Verifica si el token JWT ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrae la fecha de expiración del token JWT
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Método genérico para extraer una claim específica del token JWT
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrae todas las claims (datos) del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtiene el token de la solicitud HTTP
    public String getToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}



//package com.management.sales.Sales.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.function.Function;
//
//@Service
//public class JwtUtilService {
//    @Value("${jwt.secret}")
//    private String secret;  // La clave secreta para firmar los tokens JWT
//
//    @Value("${jwt.token-validity}")
//    private long tokenValidity;  // Duración de validez del token en milisegundos
//
//    // Genera un token JWT para un usuario específico
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//
//    // Extrae el nombre de usuario del token JWT
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    // Verifica si el token JWT es válido y no ha expirado
//    public boolean validateToken(String token) {
//        try {
//            return !isTokenExpired(token);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    // Verifica si el token JWT ha expirado
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    // Extrae la fecha de expiración del token JWT
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    // Método genérico para extraer una claim específica del token JWT
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // Extrae todas las claims (datos) del token JWT
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    // Obtiene el token de la solicitud HTTP
//    public String getToken(HttpServletRequest request) {
//        final String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//        return null;
//    }
//}
