package com.drivers.application.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.drivers.application.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public User findByName(String name);
}
