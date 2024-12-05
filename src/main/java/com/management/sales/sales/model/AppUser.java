package com.management.sales.sales.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "appUser_sequence", sequenceName = "appUser_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUser_sequence")
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;
//    private String role;

    public AppUser() {
    }
    public AppUser( String name, String email, String password, AppUserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public AppUserRole getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(AppUserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + "name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    public List<String> getAuthorityNames() {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia esto si deseas implementar lógica de expiración de cuenta
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia esto si deseas implementar lógica de bloqueo de cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia esto si deseas implementar lógica de expiración de credenciales
    }

    @Override
    public boolean isEnabled() {
        return true; // Cambia esto si deseas implementar lógica de habilitación de cuenta
    }

}
