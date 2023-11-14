package com.management.sales.Sales.dto;

public class LoginResponse {
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("El token no puede ser nulo o vacío");
        }
        this.token = token;
    }
}
