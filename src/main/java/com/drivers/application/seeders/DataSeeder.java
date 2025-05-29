package com.drivers.application.seeders;

import org.springframework.boot.CommandLineRunner;

import com.drivers.application.model.Role;
import com.drivers.application.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.drivers.application.dao.UserRepo;
@Component
public class DataSeeder implements CommandLineRunner{
    private final UserRepo  userRepository;

    public DataSeeder(UserRepo userRepository){
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public void run(String ...args){
        if(userRepository.count()==0){
            System.out.println("Data Seeding Started");
            User user1 = new User();
            try {
            user1.setUsername("hariharan");
            user1.setFirstName("hariharan");
            user1.setLastName("umapathi");
            user1.setPassword("12345"); 
            user1.setEmail("smarthariharan28@gmail.com");
            user1.setAccountNonExpired(true);
            user1.setAccountNonLocked(true); 
            user1.setCredentialsNonExpired(true);
            user1.setEnabled(true);
            user1.setMobile("8807290807");
            user1.setRole(Role.ADMIN);  
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
            
            userRepository.save(user1);
        }

    }
}
