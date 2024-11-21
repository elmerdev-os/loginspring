package com.sistemaveterinaria.controller;


import com.sistemaveterinaria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        return userService.authenticate(credentials.get("username"), credentials.get("password"))
                .map(user -> ResponseEntity.ok("Login exitoso"))
                .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        userService.resetPassword(request.get("email"));
        return ResponseEntity.ok("Si el correo existe, se envió una nueva contraseña.");
    }
}
