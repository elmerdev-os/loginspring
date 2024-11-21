package com.sistemaveterinaria.service;




import com.sistemaveterinaria.model.User;
import com.sistemaveterinaria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public Optional<User> authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public void resetPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String tempPassword = UUID.randomUUID().toString().substring(0, 8);
            user.get().setPassword(passwordEncoder.encode(tempPassword));
            userRepository.save(user.get());
            // Aquí enviaríamos un correo con el tempPassword
            System.out.println("Nueva contraseña temporal: " + tempPassword);

            emailService.sendEmail(
                    user.get().getEmail(),
                    "Recuperación de contraseña",
                    "Tu nueva contraseña temporal es: " + tempPassword
            );
        }
    }
}
