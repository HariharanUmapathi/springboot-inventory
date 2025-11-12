package com.drivers.application.seeders;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;

import com.drivers.application.model.Driver;
import com.drivers.application.model.Role;
import com.drivers.application.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.drivers.application.dao.DriverRepo;
import com.drivers.application.dao.UserRepo;
import com.drivers.application.services.RandomUserService;

@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepo userRepository;
    private final DriverRepo driverRepository;
    private final RandomUserService ranUserService;

    public DataSeeder(UserRepo userRepository, DriverRepo driverRepository, RandomUserService ranUserService) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.ranUserService = ranUserService;
    }

    @Override
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
        if(driverRepository.count()==0){
            List<String> experiences = List.of("Beginner", "Intermediate", "Experienced", "Veteran", "Expert");
            Random random = new Random();
            Map<String,Object> drivers = this.ranUserService.getDrivers();
             @SuppressWarnings("unchecked")
             
             List<Map<String,Object>>driversList = (List<Map<String,Object>>) drivers.get("results");
        for (int i = 1; i <= 250; i++) {
            Driver driver = new Driver();
            driver.setDriverId(null);
            driver.setOnduty(random.nextBoolean());
            driver.setPerKMRate(0.5 + (2.0 * random.nextDouble()));
            driver.setVehicleId(null);
            driver.setExperience(experiences.get(random.nextInt(experiences.size())));
            Map<String,Object> personMap = driversList.get(i-1);
            @SuppressWarnings("unchecked")
            Map<String, Object> nameMap = (Map<String, Object>) personMap.get("name");
            String firstName = (String) nameMap.get("first");
            String lastName = (String) nameMap.get("last");
            driver.setName(firstName+" "+lastName);

            this.driverRepository.save(driver);
        }

        System.out.println("✅ 250 Drivers seeded into the database.");
        }
    }
}
