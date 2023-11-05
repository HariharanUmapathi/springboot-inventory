package com.drivers.application.dao;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.drivers.application.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM Users u WHERE u.lastName = ?1", nativeQuery = true)
    public User findByLastName(String lastName);

    @Query(value = "SELECT * FROM Users u WHERE u.username=?1", nativeQuery = true)
    public User findByUsername(String username);

    @Query(value = "SELECT * FROM Users u WHERE u.email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
