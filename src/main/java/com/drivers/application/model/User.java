package com.drivers.application.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
@Entity
@Table(name = "Users")

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", unique = true, nullable = false)
    Integer userId;
    @Column(name = "username", unique = false, nullable = true)
    public String username;
    @Column(name = "lastName", unique = false, nullable = false)
    String lastName;
    @Column(name = "firstName", unique = false, nullable = false)
    String firstName;
    @Column(name = "email", unique = false, nullable = false)
    String email;
    @Column(name = "password", unique = false, nullable = false)
    String password;
    @Column(name = "mobile", unique = false, nullable = true)
    String mobile;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "account_non_expired", unique = false, nullable = true)
    private boolean accountNonExpired;
    @Column(name = "account_non_locked", unique = false, nullable = true)
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired", unique = false, nullable = true)
    private boolean credentialsNonExpired;
    @Column(name = "enabled", unique = false, nullable = true)
    private boolean enabled;

    public Integer getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.lastName;
    }

    public void setName(String lastName) {
        this.lastName = lastName;
    }

    public String setPassword(String rawPassword) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
        this.password = new String(salt) + ":" + new String(hashedPassword);

        return "success";
    }

    public Boolean verifyPassword(String rawPassword) throws NoSuchAlgorithmException {
        String salt = this.password.split(":")[0];
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        String verificationPassword = new String(md.digest(rawPassword.getBytes(StandardCharsets.UTF_8)));
        return this.password.compareTo(verificationPassword) == 0;
    }
    // UserDetails Methods Implementation

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can customize this to return a list of user roles/authorities
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
