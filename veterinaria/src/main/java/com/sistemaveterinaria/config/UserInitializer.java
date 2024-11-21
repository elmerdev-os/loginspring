package com.sistemaveterinaria.config;

import com.sistemaveterinaria.model.User;
import com.sistemaveterinaria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica si el usuario "admin" ya existe
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña: admin123
            admin.setEmail("admin@example.com");
            admin.setAdmin(true);

            userRepository.save(admin);
            System.out.println("Usuario administrador creado: admin/admin123");
        }
    }
}
