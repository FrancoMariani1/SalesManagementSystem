package com.management.sales.sales.dto;

import java.util.List;

public class AuthenticationResponse {
    private String jwt;
    private List<String> roles; // Lista de roles del usuario
    private String message; // Mensaje opcional

    // Constructor principal
    public AuthenticationResponse(String jwt, List<String> roles, String message) {
        this.jwt = jwt;
        this.roles = roles;
        this.message = message;
    }

    // Constructor simplificado para solo JWT
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getters y setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "jwt='" + jwt + '\'' +
                ", roles=" + roles +
                '}';
    }
}