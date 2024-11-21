package com.sistemaveterinaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "veterinariadb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private boolean isAdmin;
}
