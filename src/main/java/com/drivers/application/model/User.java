package com.drivers.application.model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Users")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", unique = true, nullable = false)
    Integer userId;
    @Column(name = "lastName", unique = false, nullable = false)
    String lastName;
    @Column(name = "firstName", unique = false, nullable = false)
    String firstName;
    @Column(name = "email", unique = false, nullable = false)
    String email;
    @Column(name = "password", unique = false, nullable = false)
    String password;
    @Column(name = "mobile", unique = false, nullable = false)
    String mobile;

    public User(String mobile, String password) {
        this.mobile = mobile;
        try {
            this.password = this.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

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

}
