package com.apidesign.restapi.config;

import com.apidesign.restapi.model.User;
import com.apidesign.restapi.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Check if we already have users
        if (userRepository.count() == 0) {
            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            Set<String> adminRoles = new HashSet<>();
            adminRoles.add("ADMIN");
            adminRoles.add("USER");
            admin.setRoles(adminRoles);
            userRepository.save(admin);

            // Create regular user
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            Set<String> userRoles = new HashSet<>();
            userRoles.add("USER");
            user.setRoles(userRoles);
            userRepository.save(user);

            System.out.println("Database initialized with sample users.");
        }
    }
}